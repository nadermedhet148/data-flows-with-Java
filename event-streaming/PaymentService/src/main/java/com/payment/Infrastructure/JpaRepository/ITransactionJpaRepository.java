package com.payment.Infrastructure.JpaRepository;

import com.payment.Infrastructure.Entites.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITransactionJpaRepository extends JpaRepository<Transaction, Integer> {
}
