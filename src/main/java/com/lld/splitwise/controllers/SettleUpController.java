package com.lld.splitwise.controllers;

import com.lld.splitwise.dtos.ResponseStatus;
import com.lld.splitwise.dtos.SettleUpTransactionDto;
import com.lld.splitwise.dtos.SettleUpUserRequestDto;
import com.lld.splitwise.dtos.SettleUpUserResponseDto;
import com.lld.splitwise.exceptions.InvalidUserException;
import com.lld.splitwise.models.Transaction;
import com.lld.splitwise.services.SettleUpService;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class SettleUpController {
    private SettleUpService settleUpService;

    private SettleUpController(SettleUpService settleUpService)
    {
        this.settleUpService = settleUpService;
    }

    public SettleUpUserResponseDto settleUpUser(SettleUpUserRequestDto requestDto) throws InvalidUserException {
       SettleUpUserResponseDto responseDto = new SettleUpUserResponseDto();
       try{
           List<Transaction> settleUpExpenses = settleUpService.settleUpUser(requestDto.getUserId());

           List<SettleUpTransactionDto> transactions = settleUpExpenses.stream().map(
                   exp -> {
                       SettleUpTransactionDto transactionDto = new SettleUpTransactionDto();
                       transactionDto.setReceiver(exp.getReceiver().toDto());
                       transactionDto.setSender(exp.getSender().toDto());
                       transactionDto.setAmount(exp.getAmount());
                       return transactionDto;
                   }
           ).toList();

           responseDto.setExpenses(transactions);
           responseDto.setResponseStatus(ResponseStatus.SUCCESS);
       } catch (InvalidUserException e) {
           responseDto.setResponseStatus(ResponseStatus.FAILURE);
       }
        return responseDto;
    }
}
