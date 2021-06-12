package com.user.Domain.Event;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public interface IEventPublisher {

    public void publish(DomainEvent ev) throws IOException, TimeoutException;
}
