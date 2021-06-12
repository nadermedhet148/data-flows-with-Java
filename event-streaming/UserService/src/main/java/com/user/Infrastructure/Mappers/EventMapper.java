package com.user.Infrastructure.Mappers;

import com.user.Domain.Event.DomainEvent;
import com.user.Infrastructure.Entites.Event;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EventMapper {
    EventMapper INSTANCE = Mappers.getMapper( EventMapper.class );

    @Mapping(source = "eventType", target = "eventType")
    @Mapping(target = "data", ignore = true)
    Event domainEventToEvent(DomainEvent event);

}
