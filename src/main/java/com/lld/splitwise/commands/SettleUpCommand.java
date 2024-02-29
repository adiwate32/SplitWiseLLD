package com.lld.splitwise.commands;

import com.lld.splitwise.controllers.SettleUpController;
import com.lld.splitwise.dtos.SettleUpUserRequestDto;
import com.lld.splitwise.dtos.SettleUpUserResponseDto;
import com.lld.splitwise.exceptions.InvalidUserException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SettleUpCommand implements Command{
    private SettleUpController settleUpController;
    public SettleUpCommand(SettleUpController settleUpController)
    {
        this.settleUpController = settleUpController;
    }

    @Override
    public void execute(String input) throws InvalidUserException {
        List<String> words = List.of(input.split(" "));

        long userId = Long.parseLong(words.get(0));

        SettleUpUserRequestDto requestDto = new SettleUpUserRequestDto();
        requestDto.setUserId(userId);

        SettleUpUserResponseDto responseDto = settleUpController.settleUpUser(requestDto);
        responseDto.getExpenses().forEach(System.out::println);
    }

    @Override
    public boolean matches(String input) {
        List<String> words = List.of(input.split(" "));

        return words.size() == 2 && words.get(1).equals(CommandKeyWords.settleUpCommand);
    }
}
