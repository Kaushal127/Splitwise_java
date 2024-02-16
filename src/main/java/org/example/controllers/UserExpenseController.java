package org.example.controllers;

import org.example.models.UserExpense;
import org.example.services.UserExpenseService;

public class UserExpenseController {
    private UserExpenseService userExpenseService ;

    public UserExpenseController(UserExpenseService userExpenseService) {
        this.userExpenseService = userExpenseService;
    }

    public UserExpense addUserExpense(String user, String expenseName, String price, String userExpenseType) {
        // input -> kaushal dinnerExpense 5000 paid / had_to_pay

        UserExpense userExpense = userExpenseService.addUserExpense(user , expenseName , price ,userExpenseType) ;
        System.out.println("Amount of " + userExpense.getAmount() +" "+userExpenseType+" by "+ userExpense.getUser().getName() );
        return userExpense ;
    }
}
