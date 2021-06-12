package com.user.Domain.Services;

import com.user.Domain.Event.DomainEvent;
import com.user.Domain.Event.IEventPublisher;
import com.user.Domain.Event.IEventRepository;
import com.user.Domain.User.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class UserService {

    private  IEventPublisher publisher;
    private  IEventRepository eventRepository;
    private IUserRepository userRepository;

    public UserService(IEventPublisher publisher,
                       IEventRepository eventRepository,
                       IUserRepository userRepository
                       ){
        this.publisher = publisher;
        this.eventRepository= eventRepository;
        this.userRepository = userRepository;
    }


    public User createUser (CreateUserCommand cm) throws IOException, TimeoutException {
        User user = new User();
        UserCreatedEvent ev =  user.process(cm);
        this.publisher.publish(ev);
        this.eventRepository.save(ev);
        return user;
    }

    public User processTransaction (ProcessTransactionCommand cm) throws IOException, TimeoutException {
        User user = this.userRepository.getUser(cm.getUserId());
        DomainEvent event = user.process(cm);
        this.publisher.publish(event);
        this.eventRepository.save(event);
        return  user;
    }

}
