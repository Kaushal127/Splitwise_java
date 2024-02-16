package org.example.command;

import org.example.controllers.UserController;
import org.example.dtos.Transaction;

import java.util.List;

public class SettleUpUserCommand implements Command{
    private static final String SETTLEUP_USER = "settleuser";
    private UserController userController ;
    public SettleUpUserCommand(UserController userController){
        this.userController = userController ;
    }
    @Override
    public void execute(String input) {
        //input ->   kaushal goaTrip settleuser
        List<String> words = List.of(input.split(" ")) ;
        List<Transaction> transactions = userController.settleUser(words.get(0), words.get(1) ) ;
    }

    @Override
    public boolean matches(String input) {
        List<String> words = List.of(input.split(" ")) ;
        return  words.get(2).equals(SETTLEUP_USER) ;
    }
}
