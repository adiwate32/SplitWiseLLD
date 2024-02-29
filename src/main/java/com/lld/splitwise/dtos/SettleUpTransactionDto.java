package com.lld.splitwise.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SettleUpTransactionDto {
    private UserDto  receiver;
    private UserDto sender;
    private int amount;

    @Override
    public String toString() {
        return "SettleUpTransactionDto{" +
                "receiver=" + receiver +
                ", sender=" + sender +
                ", amount=" + amount +
                '}';
    }
}
