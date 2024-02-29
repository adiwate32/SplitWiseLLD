package com.lld.splitwise.repositories;

import com.lld.splitwise.models.ExpenseUser;
import com.lld.splitwise.models.ExpenseUserType;
import com.lld.splitwise.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpenseUserRepository extends JpaRepository<ExpenseUser, Long> {

    public List<ExpenseUser> findAllByUser(User user);
}
