package org.example.command;


import org.example.controllers.UserExpenseController;

import java.util.List;

public class UserExpenseCommand implements Command {
    private static final String USEREXPENSE = "UserExpense";
    private UserExpenseController userExpenseController ;

    public UserExpenseCommand(UserExpenseController userExpenseController) {
        this.userExpenseController = userExpenseController;
    }

    @Override
    public void execute(String input) {
        // input -> UserExpense kaushal dinnerExpense 5000 paid / had_to_pay
        List<String> words = List.of(input.split(" ")) ;
        userExpenseController.addUserExpense(words.get(1) , words.get(2) , words.get(3) ,words.get(4)) ;
    }

    @Override
    public boolean matches(String input) {
        List<String> words = List.of(input.split(" ")) ;
        return words.get(0).equals(USEREXPENSE);
    }
}
