package com.payment.Domain.Transaction;

public interface ITransactionRepository {
    public Transaction save(Transaction transaction);
    public Transaction getOne(Integer transactionId);
}
