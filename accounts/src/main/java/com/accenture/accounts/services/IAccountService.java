package com.accenture.accounts.services;

import com.accenture.accounts.dto.AccountDto;
import com.accenture.accounts.dto.NewAccountDto;

import java.util.List;

public interface IAccountService {

    AccountDto create(NewAccountDto accountDto);

    List<AccountDto> fetchCustomerAccounts(Long customerId);
    AccountDto fetchById(Long number);


    AccountDto update(AccountDto accountDto);

}
