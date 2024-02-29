package com.lld.splitwise.commands;

import com.lld.splitwise.exceptions.InvalidUserException;
import org.springframework.stereotype.Component;

@Component
public interface Command {
    public void execute(String command) throws InvalidUserException;
    public boolean matches(String command);
}
