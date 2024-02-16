package org.example;


import org.example.controllers.UserController;
import org.example.dtos.Transaction;
import org.example.models.*;
import org.example.repositories.ExpenseRepository;
import org.example.repositories.GroupRepository;
import org.example.repositories.UserExpenseRepository;
import org.example.repositories.UserRepository;
import org.example.services.UserService;
import org.example.strategies.HeapSettleStrategy;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
       //1. create users
        User kaushal = new User("kaushal" , "1234" , "4321");
        User prasad = new User("prasad" , "1234" , "4321");
        User sanket = new User("sanket" , "1234" , "4321");
        User chetan = new User("chetan" , "1234" , "4321");
        List<User> goaGuys = new ArrayList<>() ;
        goaGuys.add(kaushal);
        goaGuys.add(prasad);
        goaGuys.add(sanket);
        goaGuys.add(chetan);

        //2.Create the group and add these users to the gruop
        Group goaTrip = new Group("GOA_TRIP") ;
        goaTrip.setUsers(goaGuys);

        //They went to goa and go for dinner
        //3.Create expenses
        Expense dinnerExpense = new Expense("GOA_TRIP" ,"Dinner", 5000 , ExpenseType.REGULAR) ;

        //4. Add the expenses share of everyone
        UserExpense kaushalShare =
                new UserExpense(kaushal ,dinnerExpense, 1000 , UserExpenseType.HAD_TO_PAY) ;
        UserExpense prasadShare =
                new UserExpense(prasad ,dinnerExpense , 1000 ,UserExpenseType.HAD_TO_PAY) ;
        UserExpense sanketShare =
                new UserExpense(sanket ,dinnerExpense , 1000, UserExpenseType.HAD_TO_PAY) ;
        UserExpense chetanShare =
                new UserExpense(chetan,dinnerExpense,2000,UserExpenseType.HAD_TO_PAY) ;
        //5.who paid what
        UserExpense PaidBykaushal =
                new UserExpense(kaushal ,dinnerExpense ,5000,UserExpenseType.PAID) ;

        // Manually add all these details to the databases/repositories
        UserRepository userRepository =  new UserRepository() ;
        GroupRepository groupRepository = new GroupRepository() ;
        UserExpenseRepository userExpenseRepository = new UserExpenseRepository() ;
        ExpenseRepository expenseRepository = new ExpenseRepository() ;

        userRepository.setUsers(goaGuys);
        goaTrip.getExpenses().add(dinnerExpense);
        groupRepository.getGroups().add(goaTrip);
        expenseRepository.getExpenses().add(dinnerExpense);

        userExpenseRepository.getUserExpenses().add(kaushalShare);
        userExpenseRepository.getUserExpenses().add(prasadShare);
        userExpenseRepository.getUserExpenses().add(sanketShare);
        userExpenseRepository.getUserExpenses().add(chetanShare);

        userExpenseRepository.getUserExpenses().add(PaidBykaushal);

        UserController userController = new UserController(
                new UserService(userExpenseRepository,groupRepository,userRepository,new HeapSettleStrategy())) ;

        List<Transaction> userTransaction = userController.settleUser("kaushal" ,"GOA_TRIP") ;

        for (Transaction transaction : userTransaction){
            System.out.println(transaction.getFrom()+ " -> "
                    +transaction.getTo()+" : "+transaction.getAmount());
        }

    }
}