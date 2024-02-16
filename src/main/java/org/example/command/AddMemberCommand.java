package org.example.command;

import org.example.controllers.GroupController;

import java.util.List;

public class AddMemberCommand implements Command{
    private static final String ADD_GROUP_MEMBER = "AddMember";
    private GroupController groupController ;

    public AddMemberCommand(GroupController groupController) {
        this.groupController = groupController;
    }

    @Override
    public void execute(String input) {
        // input -> AddMember g1 u2
        List<String> words = List.of(input.split(" ")) ;
        groupController.addMemberToGroup(words.get(1) ,words.get(2)) ;
    }

    @Override
    public boolean matches(String input) {
        List<String> words = List.of(input.split(" ")) ;
        return words.get(0).equals(ADD_GROUP_MEMBER);
    }
}
