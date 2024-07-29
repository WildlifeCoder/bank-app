package com.accenture.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
@Schema(
        name = "New Transaction DTO",
        description = "Schema to create a new transaction"
)
public class NewTransactionDto {

    @Schema(
            description = "account number"
    )
    @NotEmpty(message = "The field accountNumber should not be empty")
    private Long accountNumber;

    @Schema(
            description = "Account amount"
    )
    @NotEmpty(message = "The field amount should not be empty")
    private Float amount;
}
