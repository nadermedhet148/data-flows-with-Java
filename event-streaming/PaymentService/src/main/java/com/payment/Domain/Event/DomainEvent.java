package com.payment.Domain.Event;

import lombok.Getter;
import lombok.Setter;
import org.json.JSONObject;

import java.util.Date;

@Getter
@Setter
public abstract class DomainEvent {
    private String entityName ;

    private String entityId ;

    private String eventType;

    private Date createdAt = new Date();
    
}
