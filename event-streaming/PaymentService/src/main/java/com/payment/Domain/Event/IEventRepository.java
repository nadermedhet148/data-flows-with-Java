package com.payment.Domain.Event;

public interface IEventRepository {
    public DomainEvent save(DomainEvent event);
}
