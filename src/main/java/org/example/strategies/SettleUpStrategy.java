package org.example.strategies;

import org.example.dtos.Transaction;
import org.example.models.User;

import java.util.List;
import java.util.Map;

public interface SettleUpStrategy {
    public List<Transaction> settleUpUsers(Map<User, Integer> map) ;
}
