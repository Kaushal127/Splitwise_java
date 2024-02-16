package org.example.services;

import org.example.models.Expense;
import org.example.models.ExpenseType;
import org.example.models.Group;
import org.example.repositories.ExpenseRepository;
import org.example.repositories.GroupRepository;

import java.util.ArrayList;

public class ExpenseService {
    private ExpenseRepository expenseRepository ;
    private GroupRepository groupRepository ;

    public ExpenseService(ExpenseRepository expenseRepository, GroupRepository groupRepository) {
        this.expenseRepository = expenseRepository;
        this.groupRepository = groupRepository;
    }

    public Expense addExpense(String groupName , String description, int amount, String messege) {
        ExpenseType expenseType = null;
       if(messege == "regular"){
            expenseType = ExpenseType.REGULAR ;
        } else if (messege == "dummy"){
           expenseType = ExpenseType.DUMMY ;
       }
       Expense expense = new Expense(groupName,description,amount,expenseType);
       expenseRepository.addExpense(expense) ;
        Group group = groupRepository.findGroupByName(groupName) ;
         if (group!=null){
             group.getExpenses().add(expense);
         }
       return expense ;
    }
}
