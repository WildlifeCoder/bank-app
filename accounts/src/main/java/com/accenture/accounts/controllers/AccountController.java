package com.accenture.accounts.controllers;

import com.accenture.accounts.dto.*;
import com.accenture.accounts.services.IAccountService;
import com.accenture.accounts.services.ITransactionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "REST to handle account", description = "CRUD REST to accounts")
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api")
public class AccountController {

    @NonNull()
    private final IAccountService accountService;
    @NonNull()
    private final ITransactionService transactionService;

    @NonNull()
    private final Environment environment;

    @NonNull()
    private final SupportInfoDto supportInfoDto;


    @GetMapping(value = "/fetch-support-info")
    public ResponseEntity<SupportInfoDto> fetchSupportInfoDto() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(supportInfoDto);
    }

    @GetMapping(value = "/fetch-java-home", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> fetchJavaHome() {
      String javaHomeEnv = environment.getProperty("JAVA_HOME");
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(javaHomeEnv);
    }

    @Operation(
            summary = "Get account by account number",
            description = "Handle the request to get account number"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP STATUS OK",
                    content = @Content(
                            schema = @Schema(implementation = AccountDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "HTTP STATUS NOT FOUND",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })
    @GetMapping(value = "/fetchAccount/{accountNumber}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AccountDto> fetchAccount(@PathVariable Long accountNumber) {
        AccountDto account = accountService.fetchById(accountNumber);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(account);
    }

    @Operation(
            summary = "Get all account of customer by customerID",
            description = "Handle the request to get all accounts of customer"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP STATUS OK",
                    content = @Content(
                            schema = @Schema(implementation = AccountDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "HTTP STATUS NOT FOUND",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })
    @GetMapping(value = "/fetchCustomerAccounts/{customerId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<AccountDto>> fetchCustomerAccounts(@PathVariable Long customerId) {
        List<AccountDto> accounts = accountService.fetchCustomerAccounts(customerId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(accounts);
    }

    @Operation(
            summary = "Creation of account",
            description = "Handle the creation of account in our bank system"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "HTTP STATUS OK"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "HTTP STATUS BAD REQUEST",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })
    @PostMapping(value = "/createAccount", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AccountDto> createAccount(@RequestBody NewAccountDto accountDto) {
      AccountDto savedAccount =  accountService.create(accountDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(savedAccount);
    }

    @PutMapping(value = "/updateAccount", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AccountDto> updateAccount(@Valid @RequestBody AccountDto accountDto) {
        AccountDto updatedAccount = accountService.update(accountDto);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(updatedAccount);
    }


    @PostMapping(value = "/createTransaction", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDto> createTransaction(@RequestBody NewTransactionDto newTransactionDto) {
        transactionService.create(newTransactionDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto("201", "Transaction has been created"));
    }

    @Operation(
            summary = "Get all transactions of customer account",
            description = "Handle the request to get all transaction form customer account"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP STATUS OK",
                    content = @Content(
                            schema = @Schema(implementation = TransactionDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "HTTP STATUS NOT FOUND",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })
    @GetMapping(value = "/fetchAccountTransactions/{accountNumber}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TransactionDto>> fetchAccountTransactions(@PathVariable Long accountNumber) {
        List<TransactionDto> transactionsDtos = transactionService.fetchAccountTransactions(accountNumber);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(transactionsDtos);
    }

}
