package com.payment.Adapters.Rest;

import com.payment.Adapters.Messages.EventPubliser;
import com.payment.Adapters.Rest.requests.CreatePaymentRequest;
import com.payment.Domain.Services.TransactionService;
import com.payment.Domain.Transaction.CreateTransactionCommand;
import com.payment.Domain.Transaction.Transaction;
import com.payment.Infrastructure.RepositoryImpl.TransactionRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@RestController
@RequestMapping(value = "/transactions")

public class TransactionsController {



    @Autowired
    EventPubliser eventPubliser;

    @Autowired
    TransactionRepositoryImpl transactionRepository;

    @PostMapping(value = "")
    public Transaction createTransaction(@RequestBody CreatePaymentRequest body) throws IOException, TimeoutException {
        TransactionService transactionService = new TransactionService( eventPubliser,transactionRepository);
        Transaction transaction = transactionService.createTransaction(new CreateTransactionCommand(body.getUserId() , body.getAmount() , body.getType()));
        return transaction;
    }

}
