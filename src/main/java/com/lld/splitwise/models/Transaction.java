package com.lld.splitwise.models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Transaction {
    private User sender;
    private User receiver;
    private int amount;
}
