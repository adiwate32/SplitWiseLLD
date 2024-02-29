package com.lld.splitwise.commands;

import com.lld.splitwise.exceptions.InvalidUserException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CommandExecutor {
    private List<Command> commands;

    public CommandExecutor()
    {
        this.commands = new ArrayList<>();
    }

    public void addCommand(Command command)
    {
        commands.add(command);
    }

    public void removeCommand(Command command)
    {
        commands.remove(command);
    }

    public void execute(String input) throws InvalidUserException {
        for(Command command: commands)
        {
            if(command.matches(input))
            {
                command.execute(input);
            }
        }
    }
}
