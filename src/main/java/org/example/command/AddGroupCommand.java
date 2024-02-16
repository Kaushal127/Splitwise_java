package org.example.command;

import org.example.controllers.GroupController;
import org.example.models.Group;

import java.util.List;

public class AddGroupCommand implements Command{
    private static final String ADD_GROUP = "AddGroup";
    private GroupController groupController ;

    public AddGroupCommand(GroupController groupController) {
        this.groupController = groupController;
    }

    @Override
    public void execute(String input) {
        //input -> AddGroup Roommates
        List<String> words = List.of(input.split(" ")) ;
        Group group = groupController.createGroup(words.get(1)) ;
    }

    @Override
    public boolean matches(String input) {
        List<String> words = List.of(input.split(" ")) ;
        return words.get(0).equals(ADD_GROUP);
    }
}
