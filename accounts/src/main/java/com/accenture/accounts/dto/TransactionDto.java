package com.accenture.accounts.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data

public class TransactionDto {

    private Long transactionId;
    private LocalDateTime createdDate;
    private Float amount;
    private Float balance;

}
