package com.user.Infrastructure.RepositoryImpl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.user.Domain.Event.DomainEvent;
import com.user.Domain.Event.IEventRepository;
import com.user.Domain.User.TransactionFailedEvent;
import com.user.Domain.User.TransactionSucceedEvent;
import com.user.Domain.User.UserCreatedEvent;
import com.user.Infrastructure.Entites.Event;
import com.user.Infrastructure.JpaRepository.IEventJpaRepository;
import com.user.Infrastructure.Mappers.EventMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventRepositoryImpl implements IEventRepository {
    @Autowired
    private IEventJpaRepository eventJpaRepository;

    private ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    @Override
    public DomainEvent save(DomainEvent event) throws JsonProcessingException {
        Event ev =  EventMapper.INSTANCE.domainEventToEvent(event);
        ev.setData(objectMapper.writeValueAsString(event));
        this.eventJpaRepository.save(ev);
        return event;
    }

    @Override
    public List<DomainEvent> getEntityEvents(String entityName, String entityId) {
        List<Event> events = this.eventJpaRepository.findByEntityNameAndEntityId(entityName,entityId);
        return events.stream().map(event -> {
            DomainEvent domainEvent = null;
            try {
                if(event.getEventType().equals("UserCreated")){
                    domainEvent = objectMapper.readValue(event.getData() , UserCreatedEvent.class);
                    domainEvent.setEntityId(event.getEntityId());

                }
                if(event.getEventType().equals("TransactionFailed")){
                    domainEvent = objectMapper.readValue(event.getData() , TransactionFailedEvent.class);
                    domainEvent.setEntityId(event.getEntityId());
                }
                if(event.getEventType().equals("TransactionSucceed")){
                    domainEvent = objectMapper.readValue(event.getData() , TransactionSucceedEvent.class);
                    domainEvent.setEntityId(event.getEntityId());
                }
            } catch (JsonProcessingException e) {
                return null;
            }

            return domainEvent;
        }).filter(ele-> ele != null).collect(Collectors.toList());
    }
}
