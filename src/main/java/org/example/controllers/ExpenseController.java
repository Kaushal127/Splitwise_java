package org.example.controllers;

import org.example.models.Expense;
import org.example.models.Group;
import org.example.services.ExpenseService;

public class ExpenseController {

    private ExpenseService expenseService ;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    public Expense addExpense( String groupName, String description, String value , String expenseType) {
        int amount = Integer.parseInt(value) ;
        // input -> Expense Dinner 5000 ExpenseType.REGULAR
        Expense expense = expenseService.addExpense(groupName,description,amount,expenseType) ;
        System.out.println("Expense is created : " + expense);
        System.out.println("Expense is added in group : " + expense.getGroupName());
        return expense ;
    }
}
