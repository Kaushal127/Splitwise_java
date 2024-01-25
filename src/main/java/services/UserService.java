package services;

import dtos.Transaction;
import models.*;
import repositories.GroupRepository;
import repositories.UserExpenseRepository;
import repositories.UserRepository;
import strategies.SettleUpStrategy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserService {
    private UserExpenseRepository userExpenseRepository ;
    private GroupRepository groupRepository ;
    private SettleUpStrategy settleUpStrategy ;

    public UserService(UserExpenseRepository userExpenseRepository, GroupRepository groupRepository, SettleUpStrategy settleUpStrategy) {
        this.userExpenseRepository = userExpenseRepository;
        this.groupRepository = groupRepository;
        this.settleUpStrategy = settleUpStrategy;
    }

    public List<Transaction> settleUser(String userName, String groupName) {
          /*
               1. Get all expenses of a group
               2. Filter only regular expenses -> expenses
               3. For every expense -> find userExpenses (list)
               4. We iterate over all the userExpenses
                  Calculate the extra amount for every user

                  Map of extra amount -> Map<User, Integer>
                     Data : [A:2000, B:-1000]
                  If the type of userExpense is paidBy, extra_amount += amount;
                                                WhoHadToPay, extra_amount -= amount;
               5. Pass this map to another class, which will use heaps and gets me the
                  transactions list.
         */

        Map<User , Integer> extraAmountMap = new HashMap<>() ;
        List<Expense> expenses = groupRepository.findExpenseByGroup(groupName) ;
          for (Expense expense : expenses){
              if(expense.getExpenseType()== ExpenseType.REGULAR){
                  List<UserExpense> userExpenseList =
                          userExpenseRepository.findUserExpensesByExpense(expense.getDescription()) ;
                        for (UserExpense userExpense : userExpenseList){
                            User user = userExpense.getUser() ;
                            if (!extraAmountMap.containsKey(user)){
                                extraAmountMap.put(user,0) ;
                            }
                            Integer amount =extraAmountMap.get(user) ;
                            if (userExpense.getUserExpenseType()== UserExpenseType.PAID_BY){
                                amount += userExpense.getAmount() ;
                            } else {
                                amount -= userExpense.getAmount() ;
                            }
                            extraAmountMap.put(user , amount ) ;
                        }
              }
          }
         // finding transaction using extra amount for every user
         List<Transaction> groupTransactions = settleUpStrategy.settleUpUsers(extraAmountMap) ;
          List<Transaction> userTransactions = new ArrayList<>() ;

          for (Transaction transaction : groupTransactions){
              if(transaction.getFrom().equals(userName) ||
              transaction.getTo().equals(userName)){
                  userTransactions.add(transaction);
              }
          }
          return userTransactions ;
    }
}
