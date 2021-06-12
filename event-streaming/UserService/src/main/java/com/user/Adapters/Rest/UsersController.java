package com.user.Adapters.Rest;

import com.user.Adapters.Messages.EventPubliser;
import com.user.Adapters.Rest.requests.CreateUserRequest;
import com.user.Domain.Event.IEventRepository;
import com.user.Domain.Services.UserService;
import com.user.Domain.User.CreateUserCommand;
import com.user.Domain.User.IUserRepository;
import com.user.Domain.User.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@RestController
@RequestMapping(value = "/users")

public class UsersController {

    @Autowired
    IEventRepository eventRepository;

    @Autowired
    IUserRepository userRepository;

    @Autowired
    EventPubliser eventPubliser;


    @PostMapping(value = "")
    public Object createUser(@RequestBody CreateUserRequest body) throws  IOException, TimeoutException {
        CreateUserCommand cm = new CreateUserCommand(body.getUsername());
        UserService service = new UserService(eventPubliser , eventRepository,userRepository);
        User user = service.createUser(cm);
        return user;
    }

}
