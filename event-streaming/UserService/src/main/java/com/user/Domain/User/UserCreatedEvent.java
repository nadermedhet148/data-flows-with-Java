package com.user.Domain.User;

import com.user.Domain.Event.DomainEvent;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserCreatedEvent extends DomainEvent {

    private String username;

    private Float balance;

    public UserCreatedEvent(
            String username,
            String id,
            Float balance
    ){
        this.setEntityName("User");
        this.setEventType("UserCreated");
        this.setEntityId(id);
        this.username = username;
        this.balance = balance;

    }


}
