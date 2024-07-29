package com.accenture.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;

import lombok.Data;


@Data
@Schema(
        name = "Account",
        description = "Schema to storage the account data"
)
public class AccountDto {

    @Schema(
            description = "Id for customer"
    )
    private Long customerId;
    @Schema(
            description = "Account number"
    )
    private Long accountNumber;

    @Schema(
            description = "Account type of the client account"
    )
    @NotEmpty(message = "The field Account type should not be empty")
    private String accountType;

    @Schema(
            description = "Account branch"
    )
    @NotEmpty(message = "The field Branch should not be empty")
    private String branch;

    @Schema(
            description = "Account balance"
    )
    @NotEmpty(message = "The field balance should not be empty")
    private Float balance;

}
