package org.example.command;

import org.example.controllers.ExpenseController;

import java.util.List;

public class ExpenseCommand implements Command{
    private static final String EXPENSE = "Expense";
    private ExpenseController expenseController ;

    public ExpenseCommand(ExpenseController expenseController) {
        this.expenseController = expenseController;
    }

    @Override
    public void execute(String input) {
        // input -> Expense Goa Dinner 5000 ExpenseType.REGULAR
        List<String> words = List.of(input.split(" ")) ;
        expenseController.addExpense(words.get(1) , words.get(2) ,words.get(3) , words.get(4) ) ;
    }

    @Override
    public boolean matches(String input) {
        List<String> words = List.of(input.split(" ")) ;
        return words.get(0).equals(EXPENSE);
    }
}
