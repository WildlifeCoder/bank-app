package com.accenture.accounts.repository;

import com.accenture.accounts.entities.Account;
import com.accenture.accounts.entities.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transactions, Long> {

    List<Transactions> findAllByAccountNumberOrderByTransactionId(Long accountNumber);

}
