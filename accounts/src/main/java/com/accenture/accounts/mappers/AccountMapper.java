package com.accenture.accounts.mappers;

import com.accenture.accounts.dto.AccountDto;
import com.accenture.accounts.dto.NewAccountDto;
import com.accenture.accounts.entities.Account;

public class AccountMapper {

    public static AccountDto mapToAccountToDto(Account account, AccountDto accountDto) {
        accountDto.setAccountNumber(account.getAccountNumber());
        accountDto.setAccountType(account.getAccountType());
        accountDto.setBalance(account.getBalance());
        accountDto.setBranch(account.getBranch());
        accountDto.setCustomerId(account.getCustomerId());
        return accountDto;
    }

    public static Account mapToAccount(AccountDto accountDto, Account account) {
        account.setAccountNumber(accountDto.getAccountNumber());
        account.setAccountType(accountDto.getAccountType());
        account.setBranch(accountDto.getBranch());
        account.setCustomerId(accountDto.getCustomerId());
        account.setBalance(accountDto.getBalance());
        return account;
    }

    public static Account mapToNewAccount(NewAccountDto accountDto, Account account) {

        account.setAccountType(accountDto.getAccountType());
        account.setBranch(accountDto.getBranch());
        account.setCustomerId(accountDto.getCustomerId());

        return account;
    }
}
