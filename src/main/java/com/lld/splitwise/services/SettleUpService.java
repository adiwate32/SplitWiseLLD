package com.lld.splitwise.services;

import com.lld.splitwise.exceptions.InvalidUserException;
import com.lld.splitwise.models.Expense;
import com.lld.splitwise.models.ExpenseUser;
import com.lld.splitwise.models.Transaction;
import com.lld.splitwise.models.User;
import com.lld.splitwise.repositories.ExpenseUserRepository;
import com.lld.splitwise.repositories.UserRepository;
import com.lld.splitwise.strategies.SettleUpStrategy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class SettleUpService {
    private UserRepository userRepository;
    private ExpenseUserRepository expenseUserRepository;
    private SettleUpStrategy settleUpStrategy;

    public SettleUpService(UserRepository userRepository, ExpenseUserRepository expenseUserRepository, SettleUpStrategy settleUpStrategy)
    {
        this.userRepository = userRepository;
        this.expenseUserRepository = expenseUserRepository;
        this.settleUpStrategy = settleUpStrategy;
    }

    public List<Transaction> settleUpUser(long userId) throws InvalidUserException {
        Optional<User> optionalUser = userRepository.findById(userId);

        if(optionalUser.isEmpty())
        {
            throw  new InvalidUserException("Invalid UserId");
        }

        User user = optionalUser.get();

        Set<Expense> expenseSet = new HashSet<>();
        List<ExpenseUser> expenseUsers = expenseUserRepository.findAllByUser(user);

        for(ExpenseUser expenseUser: expenseUsers)
        {
            expenseSet.add(expenseUser.getExpense());
        }

        List<Transaction> settleUpExpenses = settleUpStrategy.settleUp(expenseSet.stream().toList());

        List<Transaction> finalSettleUpExpenses = new ArrayList<>();
        for(Transaction transaction: settleUpExpenses)
        {
            if(transaction.getReceiver().getId() == userId|| transaction.getSender().getId() == userId)
            {
                finalSettleUpExpenses.add(transaction);
            }
        }

        return finalSettleUpExpenses;
    }
}
