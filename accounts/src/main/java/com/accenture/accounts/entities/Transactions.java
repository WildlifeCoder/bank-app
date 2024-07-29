package com.accenture.accounts.entities;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Transactions extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_id", updatable = false)
    private Long transactionId;

    @Column(name = "account_number", updatable = false)
    private Long accountNumber;

    @Column(name = "amount" )
    private Float amount;

    @Column(name = "balance" )
    private Float balance;

}
