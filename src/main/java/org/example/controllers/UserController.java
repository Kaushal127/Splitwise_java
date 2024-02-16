package org.example.controllers;

import org.example.dtos.Transaction;
import org.example.models.User;
import org.example.services.UserService;

import java.util.List;

public class UserController {
    private UserService userService ;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    public List<Transaction> settleUser(String userName , String groupName){

        List<Transaction> transactions = userService.settleUser(userName,groupName) ;
           for (Transaction transaction : transactions){
               System.out.println(transaction.getFrom()+ " -> "
                       +transaction.getTo()+" : "+transaction.getAmount());
           }
        return transactions ;
    }

    public User registerUser(List<String> words) {
        //input-> Register vinsmokesanji 003 namisswwaann

        User user = userService.registerUser(words) ;
        System.out.println("User is created :"+user);
        return user ;
    }
}
