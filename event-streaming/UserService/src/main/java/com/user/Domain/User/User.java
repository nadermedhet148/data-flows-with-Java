package com.user.Domain.User;

import com.user.Domain.Event.DomainEvent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {

    private String username;

    private String userId;

    private Float balance;

    public UserCreatedEvent process(CreateUserCommand cm){
        UUID userId =  UUID.randomUUID();
        UserCreatedEvent event = new UserCreatedEvent(
                cm.getUsername() ,
                userId.toString(),
                0f
                );
        return  event;
    }

    public DomainEvent process(ProcessTransactionCommand cm){

        switch (cm.getType()){
            case "W":
                if(balance - cm.getAmount() > 0){
                    return new TransactionSucceedEvent(cm.getTransactionId(),userId,cm.getType(),cm.getAmount());
                }else {
                    return new TransactionFailedEvent(cm.getTransactionId(),userId ,"Balance not enough");
                }
            case "D":
                return new TransactionSucceedEvent(cm.getTransactionId(),userId,cm.getType(),cm.getAmount());
            default :
                return new TransactionFailedEvent(cm.getTransactionId(),userId ,"Type not allowed");
        }

    }

    public void apply(UserCreatedEvent ev) {
        this.userId = ev.getEntityId();
        this.username = ev.getUsername();
        this.balance = ev.getBalance();
    }

    public void apply(TransactionSucceedEvent ev) {
        switch (ev.getType()){
            case "W":
                this.balance -= ev.getAmount();
            case "D":
                this.balance += ev.getAmount();
        }
    }

    public void apply(TransactionFailedEvent ev) {

    }

    public void apply(DomainEvent ev) {
        if(ev instanceof UserCreatedEvent){
            apply((UserCreatedEvent) ev);
        }
        if(ev instanceof TransactionSucceedEvent){
            apply((TransactionSucceedEvent) ev);
        }
        if(ev instanceof TransactionFailedEvent){
            apply((TransactionFailedEvent) ev);
        }
    }


}
