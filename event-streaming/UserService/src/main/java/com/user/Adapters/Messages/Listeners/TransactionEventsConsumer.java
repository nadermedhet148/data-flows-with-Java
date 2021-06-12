package com.user.Adapters.Messages.Listeners;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.DeliverCallback;
import com.user.Adapters.Messages.EventConsumer;
import com.user.Adapters.Messages.EventPubliser;
import com.user.Domain.Event.IEventRepository;
import com.user.Domain.Services.UserService;
import com.user.Domain.User.IUserRepository;
import com.user.Domain.User.ProcessTransactionCommand;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@Component
public class TransactionEventsConsumer  extends EventConsumer {

    @Autowired
    IEventRepository eventRepository;

    @Autowired
    EventPubliser eventPubliser;

    @Autowired
    IUserRepository userRepository;

    @Override
    public void eventConsume() throws IOException, TimeoutException {
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String messageBody = new String(delivery.getBody(), "UTF-8");
            JSONObject json = new JSONObject(messageBody);
            UserService service = new UserService(eventPubliser , eventRepository,userRepository);
            ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

            switch (json.getString("eventType")){
                case "TransactionCreated" :
                    try {
                        service.processTransaction(objectMapper.readValue(messageBody , ProcessTransactionCommand.class ));
                    } catch (TimeoutException e) {
                        e.printStackTrace();
                    }


            }

            System.out.println(" [x] Received '" + messageBody + "'");
        };

        this.consume("Transaction_QUEUE",deliverCallback);
    }

}
