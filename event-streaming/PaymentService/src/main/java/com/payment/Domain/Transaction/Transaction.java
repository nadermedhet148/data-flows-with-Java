package com.payment.Domain.Transaction;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Transaction {

    private Integer transactionId;

    private String userId;

    private Float amount;

    private String type;

    private String status;

    private Date createdAt = new Date();

    public  TransactionCreatedEvent process(CreateTransactionCommand cm){
        this.setAmount(cm.getAmount());
        this.setUserId(cm.getUserId());
        this.setType(cm.getType());
        this.setStatus(TransactionStatus.PENDING);
        TransactionCreatedEvent event = new TransactionCreatedEvent(
                this.getTransactionId(),
                this.getUserId() ,
                this.getAmount() ,
                this.getType());
        return event;
    }


}
