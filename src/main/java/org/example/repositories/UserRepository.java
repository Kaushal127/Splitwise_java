package org.example.repositories;

import org.example.models.User;

import java.util.ArrayList;
import java.util.List;

public class UserRepository {
    private List<User> users = new ArrayList<>() ;

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public void saveUser(User user) {
        users.add(user);
    }
    public User findUserByName(String name){
        for (User user : users){
            if(user.getName().equals(name)){
                return user ;
            }
        }
        return null ;
    }
}
