package com.user.Domain.User;

import com.user.Domain.Event.DomainEvent;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.json.JSONObject;

@Getter
@Setter
@NoArgsConstructor
public class TransactionSucceedEvent extends DomainEvent {

    private int transactionId;
    private String userId;
    private String type;
    private Float amount;


    TransactionSucceedEvent(
            int transactionId,
            String userId,
            String type,
            Float amount
    ){
        this.setEntityName("User");
        this.setEntityId(userId);
        this.setEventType("TransactionSucceed");
        this.userId = userId;
        this.type = type;
        this.amount = amount;
        this.transactionId = transactionId;

    }


}
