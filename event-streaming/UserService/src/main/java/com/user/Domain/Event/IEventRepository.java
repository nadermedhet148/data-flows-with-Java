package com.user.Domain.Event;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

public interface IEventRepository {
    public DomainEvent save(DomainEvent event) throws JsonProcessingException;
    public List<DomainEvent> getEntityEvents(String entityName , String entityId);

}
