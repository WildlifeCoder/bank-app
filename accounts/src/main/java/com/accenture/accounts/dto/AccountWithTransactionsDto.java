package com.accenture.accounts.dto;



import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
@Schema(
        name = "Account with all transactions",
        description = "Schema to storage all account transactions"
 )
public class AccountWithTransactionsDto {

    private AccountDto account;
    private List<TransactionDto> transactions;


}
