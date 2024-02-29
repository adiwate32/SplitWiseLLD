package com.lld.splitwise.dtos;

import com.lld.splitwise.models.Expense;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SettleUpUserResponseDto {
    private List<SettleUpTransactionDto> expenses;
    private ResponseStatus responseStatus;
}
