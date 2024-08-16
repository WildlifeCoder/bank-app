package com.accenture.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
@Schema(
        name = "AccountWithCustomerDto",
        description = "Schema to storage the customer with associated accounts"
)
public class AccountWithCustomerDto {

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

    @Schema(
            description = "Address of the client"
    )
    @Size(min = 20, max = 150,message = "The address should be between 20 and 150 characteres")
    private String address;

    private CustomerDto customer;
}

