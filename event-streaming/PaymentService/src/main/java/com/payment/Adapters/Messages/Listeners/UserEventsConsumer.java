package com.payment.Adapters.Messages.Listeners;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.payment.Adapters.Messages.EventConsumer;
import com.payment.Adapters.Messages.EventPubliser;
import com.payment.Domain.Event.IEventRepository;
import com.payment.Domain.Services.TransactionService;
import com.payment.Domain.Transaction.AcceptTransactionCommand;
import com.payment.Domain.Transaction.CreateTransactionCommand;
import com.payment.Domain.Transaction.ITransactionRepository;
import com.payment.Domain.Transaction.RejectTransactionCommand;
import com.rabbitmq.client.DeliverCallback;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@Component
public class UserEventsConsumer extends EventConsumer {


    @Autowired
    EventPubliser eventPubliser;

    @Autowired
    ITransactionRepository transactionRepository;



    @Override
    @Transactional
    public void eventConsume() throws IOException, TimeoutException {
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String messageBody = new String(delivery.getBody(), "UTF-8");
            JSONObject json = new JSONObject(messageBody);
            TransactionService service = new TransactionService(eventPubliser ,transactionRepository);
            ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            switch (json.getString("eventType")){
                case "TransactionSucceed" :
                        service.acceptTransaction(objectMapper.readValue(messageBody , AcceptTransactionCommand.class ));
                        return;
                case "TransactionFailed" :
                        service.rejectTransaction(objectMapper.readValue(messageBody , RejectTransactionCommand.class ));
                        return;
            }

            System.out.println(" [x] Received '" + messageBody + "'");
        };

        this.consume("User_QUEUE",deliverCallback);
    }

}
