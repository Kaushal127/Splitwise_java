package org.example.repositories;

import org.example.models.Expense;
import org.example.models.Group;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupRepository {
    private UserRepository userRepository ;


    private List<Group> groups = new ArrayList<>() ;
    private Map<String , List<String> > groupMemberListMap = new HashMap<>() ;


    public List<Group> getGroups() {
        return groups;
    }


    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

    public List<Expense> findExpenseByGroup(String groupName) {
            for (Group group : groups){
                if (groupName.equals(group.getName())) {
                    return group.getExpenses() ;
                }
            }
            return new ArrayList<>() ;
        }

    public void save(Group group) {
     groups.add(group);
    }

    public Group findGroupByName(String groupName) {
        for (Group group : groups){
            if (group.getName().equals(groupName)){
                return group ;
            }
        }
        return null;
    }
}
