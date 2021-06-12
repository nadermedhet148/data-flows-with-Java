package com.payment.Domain.Transaction;

import com.payment.Domain.Event.DomainEvent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.json.JSONObject;

import java.util.Date;

@Setter
@Getter
public class TransactionCreatedEvent extends DomainEvent {
    private Integer transactionId;

    private String userId;

    private Float amount;

    private String type;

    public TransactionCreatedEvent(
            Integer transactionId,
            String userId,
            Float amount,
            String type
    ){
        this.setEntityName("Transaction");
        this.setEntityId(String.valueOf(transactionId));
        this.setEventType("TransactionCreated");
        this.userId = userId;
        this.amount = amount;
        this.type = type;

    }


}
