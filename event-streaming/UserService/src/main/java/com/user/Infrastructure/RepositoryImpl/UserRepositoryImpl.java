package com.user.Infrastructure.RepositoryImpl;

import com.user.Domain.Event.DomainEvent;
import com.user.Domain.User.IUserRepository;
import com.user.Domain.User.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRepositoryImpl implements IUserRepository {

    @Autowired
    private EventRepositoryImpl eventRepository;

    public User getUser(String userId){
        List<DomainEvent> userEvents =  this.eventRepository.getEntityEvents("User" , userId);
        User user = new User();
        userEvents.forEach(event->{
            user.apply(event);
        });
        return user;
    }
}
