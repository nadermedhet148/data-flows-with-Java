package com.user.Domain.User;

import com.user.Domain.Event.DomainEvent;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.json.JSONObject;

@Getter
@Setter
@NoArgsConstructor
public class TransactionFailedEvent extends DomainEvent {

    private int transactionId;
    private String userId;
    private String reason;


    TransactionFailedEvent(
            int transactionId,
            String userId,
            String reason
    ){
        this.setEntityName("User");
        this.setEntityId(userId);
        this.setEventType("TransactionFailed");
        this.userId = userId;
        this.transactionId = transactionId;
        this.reason = reason;
    }

}
