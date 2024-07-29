package com.accenture.accounts.services.implementation;

import com.accenture.accounts.dto.AccountDto;
import com.accenture.accounts.dto.NewAccountDto;
import com.accenture.accounts.entities.Account;

import com.accenture.accounts.exceptions.ResourceNotFound;
import com.accenture.accounts.mappers.AccountMapper;
import com.accenture.accounts.repository.AccountRepository;
import com.accenture.accounts.services.IAccountService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


import java.util.Random;

@Service
@AllArgsConstructor
public class AccountService implements IAccountService {
    private AccountRepository accountRepository;

    @Override
    public AccountDto create(NewAccountDto accountDto) {
        Account account = AccountMapper.mapToNewAccount(accountDto, new Account());
        Long accountNumber = 100000000L + new Random().nextInt(900000000);
        account.setAccountNumber(accountNumber);
        account.setBalance(0.0F);

        Account savedAccount = accountRepository.save(account);

        return AccountMapper.mapToAccountToDto(savedAccount, new AccountDto());
    }

    @Override
    public List<AccountDto> fetchCustomerAccounts(Long customerId) {
        List<AccountDto> accountDtos = new ArrayList<>();
        List<Account> accountList = accountRepository.findAllByCustomerIdOrderByAccountNumber(customerId);

        for(Account account: accountList) {
            accountDtos.add(AccountMapper.mapToAccountToDto(account, new AccountDto()));
        }

        return accountDtos;
    }

    @Override
    public AccountDto fetchById(Long accountNumber) {

        Account account = accountRepository.findById(accountNumber).orElseThrow(
                () -> new ResourceNotFound("Account", "Number account", accountNumber.toString())
        );

        return AccountMapper.mapToAccountToDto(account, new AccountDto());

    }

    @Override
    public AccountDto update(AccountDto accountDto) {
       Account account = accountRepository.findById(accountDto.getCustomerId()).orElseThrow(
               () -> new ResourceNotFound("Account", "Id", accountDto.getCustomerId().toString())
       );
       AccountMapper.mapToAccount(accountDto, account);
       accountRepository.save(account);
       return accountDto;
    }

}
