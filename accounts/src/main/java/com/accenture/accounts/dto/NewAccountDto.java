package com.accenture.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
@Schema(
        name = "New Account Dto",
        description = "Schema to create a new account"
)
public class NewAccountDto {

    @Schema(
            description = "Id for customer"
    )
    @NotEmpty(message = "The field customerId should not be empty")
    private Long customerId;


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

}
