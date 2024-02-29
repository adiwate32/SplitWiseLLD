package com.lld.splitwise.strategies;

import com.lld.splitwise.models.Expense;
import com.lld.splitwise.models.ExpenseUser;
import com.lld.splitwise.models.ExpenseUserType;
import com.lld.splitwise.models.Transaction;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

@Service
public class HeapSettleUpStrategy implements SettleUpStrategy{
    @Override
    public List<Transaction> settleUp(List<Expense> expenses) {
        PriorityQueue<ExpenseUser> paidBy = new PriorityQueue<>(Comparator.comparingInt(ExpenseUser::getAmount));
        PriorityQueue<ExpenseUser> hadToPay = new PriorityQueue<>(Comparator.comparingInt(ExpenseUser::getAmount));

        List<Transaction> settleUpExpenses = new ArrayList<>();

        for(Expense expense: expenses)
        {
            for(ExpenseUser expenseUser: expense.getExpenseUsers())
            {
                if(expenseUser.getExpenseUserType().equals(ExpenseUserType.PAID_BY))
                {
                    paidBy.add(expenseUser);
                }

                if(expenseUser.getExpenseUserType().equals(ExpenseUserType.HAD_TO_PAY))
                {
                    hadToPay.add(expenseUser);
                }
            }
        }

        while(!paidBy.isEmpty() && !hadToPay.isEmpty())
        {
            ExpenseUser firstPos = paidBy.poll();
            ExpenseUser firstNeg = hadToPay.poll();
            int amount = firstPos.getAmount();
            if(firstPos.getAmount() > firstNeg.getAmount())
            {
                amount = firstNeg.getAmount();
                firstPos.setAmount(firstPos.getAmount() - firstNeg.getAmount());
                paidBy.add(firstPos);
            }
            else if(firstPos.getAmount() < firstNeg.getAmount()){
                firstNeg.setAmount(firstNeg.getAmount() - firstPos.getAmount());
                hadToPay.add(firstNeg);
            }

            Transaction transaction = Transaction.builder().sender(firstNeg.getUser()).receiver(firstPos.getUser()).amount(amount).build();
            settleUpExpenses.add(transaction);
        }

        return settleUpExpenses;
    }
}
