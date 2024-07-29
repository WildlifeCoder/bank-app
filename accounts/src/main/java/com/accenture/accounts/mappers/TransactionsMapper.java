package com.accenture.accounts.mappers;

import com.accenture.accounts.dto.AccountDto;
import com.accenture.accounts.dto.NewTransactionDto;
import com.accenture.accounts.dto.TransactionDto;
import com.accenture.accounts.entities.Account;
import com.accenture.accounts.entities.Transactions;

public class TransactionsMapper {
    public static TransactionDto mapToTransactionToDto(Transactions transactions, TransactionDto transactionDto) {
        transactionDto.setTransactionId(transactions.getTransactionId());
        transactionDto.setBalance(transactions.getBalance());
        transactionDto.setAmount(transactions.getAmount());
        transactionDto.setCreatedDate(transactions.getCreatedDate());
        return transactionDto;
    }

    public static Transactions mapToTransactions(TransactionDto transactionDto, Transactions transactions) {
        transactions.setTransactionId(transactionDto.getTransactionId());
        transactions.setBalance(transactionDto.getBalance());
        transactions.setAmount(transactionDto.getAmount());
        transactions.setCreatedDate(transactionDto.getCreatedDate());
        return transactions;
    }

    public static Transactions mapToNewTransaction(NewTransactionDto transactionDto, Transactions transactions) {
        transactions.setAccountNumber(transactionDto.getAccountNumber());
        transactions.setAmount(transactionDto.getAmount());
        return transactions;
    }
}
