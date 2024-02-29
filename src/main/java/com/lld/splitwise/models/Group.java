package com.lld.splitwise.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity(
        name = "splitwise_groups"
)
public class Group extends BaseModel{
    @ManyToOne
    User admin;
    @ManyToMany
    List<User> users;
    String name;
    @OneToMany(mappedBy = "group")
    private List<Expense> expenses;
}
