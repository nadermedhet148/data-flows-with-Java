package com.payment.Adapters.Messages;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import com.payment.Adapters.Messages.RMQ.RMQBase;
import com.payment.Domain.Event.DomainEvent;
import com.payment.Domain.Event.IEventPublisher;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

@Service
public class EventPubliser implements IEventPublisher {
    RMQBase rmqBase = new RMQBase();

    public void publish(DomainEvent ev) throws IOException, TimeoutException {

        ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        String QUEUE_NAME = ev.getEntityName() + "_QUEUE";
        String message = objectMapper.writeValueAsString(ev) ;
        Channel channel = this.rmqBase.getChannel();
                    channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes(StandardCharsets.UTF_8));
            System.out.println(" [x] Sent '" + message + "'");
    }
}
