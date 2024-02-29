package com.lld.splitwise.models;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity(
        name = "expenses"
)
public class Expense extends BaseModel{
    int amount;
    String description;
    @ManyToOne
    User createdBy;
    @Enumerated(EnumType.ORDINAL)
    ExpenseType expenseType;
    @OneToMany(mappedBy = "expense", fetch = FetchType.EAGER)
    List<ExpenseUser> expenseUsers;
    @ManyToOne
    Group group;
}
