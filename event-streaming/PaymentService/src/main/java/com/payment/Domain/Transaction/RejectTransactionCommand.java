package com.payment.Domain.Transaction;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RejectTransactionCommand {
    private String userId;

    private Integer transactionId;

    private String reason;

}
