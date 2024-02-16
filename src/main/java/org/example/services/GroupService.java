package org.example.services;

import org.example.models.Group;
import org.example.models.User;
import org.example.repositories.GroupRepository;
import org.example.repositories.UserRepository;

public class GroupService {
    private GroupRepository groupRepository ;
    private UserRepository userRepository ;

    public GroupService(GroupRepository groupRepository , UserRepository userRepository) {
        this.groupRepository = groupRepository;
        this.userRepository = userRepository ;
    }

    public Group createGroup(String s) {
        Group group = new Group(s) ;
        groupRepository.save(group) ;
        return group ;
    }

    public Group addMemberToGroup(String groupName, String member) {
        Group group = groupRepository.findGroupByName(groupName) ;

        if (group!=null){
            User user = userRepository.findUserByName(member);
            group.getUsers().add(user);
        }
        return group ;
    }
}
