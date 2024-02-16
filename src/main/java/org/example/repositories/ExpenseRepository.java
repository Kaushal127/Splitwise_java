package org.example.repositories;

import org.example.models.Expense;

import java.util.ArrayList;
import java.util.List;

public class ExpenseRepository {
    private List<Expense> expenses = new ArrayList<>() ;

    public List<Expense> getExpenses() {
        return expenses;
    }

    public void setExpenses(List<Expense> expenses) {
        this.expenses = expenses;
    }

    public void addExpense(Expense expense) {
        expenses.add(expense);
    }

    public Expense findExpenseByName(String expenseName) {
        for(Expense expense : expenses){
            if(expense.getDescription().equals(expenseName)){
                return expense ;
            }
        }
        return null ;
    }
}
