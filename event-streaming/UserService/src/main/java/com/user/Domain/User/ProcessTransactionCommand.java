package com.user.Domain.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProcessTransactionCommand {

    private int transactionId;

    private String userId;

    private Float amount;

    private String type;
}
