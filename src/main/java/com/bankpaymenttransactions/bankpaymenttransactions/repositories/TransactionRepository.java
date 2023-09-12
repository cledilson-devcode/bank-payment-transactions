package com.bankpaymenttransactions.bankpaymenttransactions.repositories;

import com.bankpaymenttransactions.bankpaymenttransactions.domain.tansaction.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
