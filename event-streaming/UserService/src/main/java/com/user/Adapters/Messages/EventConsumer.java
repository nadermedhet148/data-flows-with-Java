package com.user.Adapters.Messages;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;
import com.user.Adapters.Messages.RMQ.RMQBase;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class EventConsumer {
    RMQBase rmqBase = new RMQBase();

    public EventConsumer(){

    }

    public void consume(String QUEUE_NAME , DeliverCallback deliverCallback) throws IOException, TimeoutException {
        Channel channel = this.rmqBase.getChannel();
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> { });
    }

    public void eventConsume() throws IOException, TimeoutException {
    }
}
