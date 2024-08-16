package com.accenture.accounts.services.customer;

import com.accenture.accounts.dto.CustomerDto;
import jakarta.validation.constraints.NotEmpty;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("customers")
public interface AccountFeignCustomer {
    @GetMapping(value = "/api/fetchById/{customerId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CustomerDto> fetchById(
            @PathVariable
            Long customerId);
}
