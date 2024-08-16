package com.accenture.accounts.controllers;;

import com.accenture.accounts.dto.AccountWithCustomerDto;
import com.accenture.accounts.services.IAccountService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotEmpty;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "REST to handle clients", description = "CRUD REST to handle clients")
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api")
public class ClientsController {
    @NonNull
    private final IAccountService accountService;

    @GetMapping(value = "/fetchAccountWithCustomer", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AccountWithCustomerDto> fetchAccountWithCustomerByAccountNumber(
            @RequestParam
            @NotEmpty(message = "The field document should not be empty")
            Long accountNumber) {
        AccountWithCustomerDto accountsWithCustomer = accountService.fetchAccountWithCustomerByAccountNumber(accountNumber);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(accountsWithCustomer);
    }
}
