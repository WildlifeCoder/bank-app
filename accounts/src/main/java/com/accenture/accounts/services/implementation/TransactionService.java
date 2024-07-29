package com.accenture.accounts.services.implementation;

import com.accenture.accounts.dto.NewTransactionDto;
import com.accenture.accounts.dto.TransactionDto;
import com.accenture.accounts.entities.Account;
import com.accenture.accounts.entities.Transactions;
import com.accenture.accounts.exceptions.ResourceNotFound;
import com.accenture.accounts.mappers.TransactionsMapper;
import com.accenture.accounts.repository.AccountRepository;
import com.accenture.accounts.repository.TransactionRepository;
import com.accenture.accounts.services.ITransactionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class TransactionService implements ITransactionService {

    private TransactionRepository transactionRepository;
    private AccountRepository accountRepository;

    @Override
    public void create(NewTransactionDto newTransactionDto) {
        Account account = accountRepository.findById(newTransactionDto.getAccountNumber()).orElseThrow(
                () -> new ResourceNotFound("Account", "Number", newTransactionDto.getAccountNumber().toString())
        );
        Transactions transactions = TransactionsMapper.mapToNewTransaction(newTransactionDto, new Transactions());

        transactions.setBalance(account.getBalance() + newTransactionDto.getAmount());

        account.setBalance(transactions.getBalance());
        accountRepository.save(account);
        transactionRepository.save(transactions);
    }

    @Override
    public List<TransactionDto> fetchAccountTransactions(Long accountNumber) {
        List<TransactionDto> transactionsDtos = new ArrayList<>();
        List<Transactions> transactionsList = transactionRepository.findAllByAccountNumberOrderByTransactionId(accountNumber);

        for(Transactions transactions: transactionsList) {
            transactionsDtos.add(TransactionsMapper.mapToTransactionToDto(transactions, new TransactionDto()));
        }

        return transactionsDtos;

    }
}
