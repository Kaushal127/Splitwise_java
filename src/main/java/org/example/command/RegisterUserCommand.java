package org.example.command;

import org.example.controllers.UserController;
import org.example.models.User;

import java.util.List;

public class RegisterUserCommand implements Command{
    private static final String REGISTER_USER = "Register";
    private UserController userController ;

    public RegisterUserCommand(UserController userController) {
        this.userController = userController;
    }


    @Override
    public void execute(String input) {
        //input-> Register vinsmokesanji 003 namisswwaann
        List<String> words = List.of(input.split(" ")) ;
        User user = userController.registerUser(words) ;
    }


    @Override
    public boolean matches(String input) {
        List<String> words = List.of(input.split(" ")) ;
        return words.get(0).equals(REGISTER_USER) ;
    }
}
