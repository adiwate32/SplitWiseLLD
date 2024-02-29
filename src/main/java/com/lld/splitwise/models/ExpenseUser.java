package com.lld.splitwise.models;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@Entity(
        name = "expenses_users"
)
public class ExpenseUser extends BaseModel{
    @ManyToOne
    Expense expense;
    @ManyToOne
    User user;
    @Enumerated(EnumType.ORDINAL)
    ExpenseUserType expenseUserType;
    @NonNull
    int amount;
}
