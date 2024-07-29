package com.accenture.accounts.services;

import com.accenture.accounts.dto.AccountDto;
import com.accenture.accounts.dto.NewTransactionDto;
import com.accenture.accounts.dto.TransactionDto;

import java.util.List;

public interface ITransactionService {

    void create(NewTransactionDto newTransactionDto);

    List<TransactionDto> fetchAccountTransactions(Long accountNumber);


}
