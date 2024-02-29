package com.lld.splitwise.strategies;

import com.lld.splitwise.models.Expense;
import com.lld.splitwise.models.Transaction;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SettleUpStrategy {
    public List<Transaction> settleUp(List<Expense> expenses);
}
