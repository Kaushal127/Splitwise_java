package org.example.controllers;

import org.example.models.Group;
import org.example.services.GroupService;

public class GroupController {
    private GroupService groupService ;

    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    public Group createGroup(String s) {

        Group group = groupService.createGroup(s) ;
        System.out.println("Group is created : " + group);
        return group ;
    }

    public Group addMemberToGroup(String groupName, String member) {

        Group group = groupService.addMemberToGroup(groupName,member) ;
        System.out.println(member + " is added to the group : "+ groupName + group);
        return group ;
    }
}
