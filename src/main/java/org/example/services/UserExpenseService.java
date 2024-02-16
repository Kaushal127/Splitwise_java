package org.example.services;

import org.example.models.Expense;
import org.example.models.User;
import org.example.models.UserExpense;
import org.example.models.UserExpenseType;
import org.example.repositories.UserRepository;
import org.example.repositories.ExpenseRepository;
import org.example.repositories.UserExpenseRepository;

public class UserExpenseService {
    private UserExpenseRepository userExpenseRepository ;
    private UserRepository userRepository ;
    private ExpenseRepository expenseRepository ;

    public UserExpenseService(UserExpenseRepository userExpenseRepository, UserRepository userRepository, ExpenseRepository expenseRepository) {
        this.userExpenseRepository = userExpenseRepository;
        this.userRepository = userRepository;
        this.expenseRepository = expenseRepository;
    }

    public UserExpense addUserExpense(String user, String expenseName, String price, String userExpenseType) {
        int amount = Integer.parseInt(price) ;
        UserExpenseType expenseType = null ;
          if(userExpenseType == "paid"){
              expenseType = UserExpenseType.PAID ;
          } else if (userExpenseType == "had_to_pay"){
              expenseType = UserExpenseType.HAD_TO_PAY ;
          }
        User newUser = userRepository.findUserByName(user) ;
        Expense expense = expenseRepository.findExpenseByName(expenseName) ;
        UserExpense userExpense = new UserExpense(newUser, expense ,amount ,expenseType) ;
        userExpenseRepository.addUserExpense(userExpense) ;
        return userExpense ;
    }
}
