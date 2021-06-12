package com.user.Domain.Event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.json.JSONObject;

import java.util.Date;

@Getter
@Setter
public abstract class DomainEvent {
    private String entityName ;

    private String entityId ;

    private Date createdAt = new Date();

    private String eventType;



}
