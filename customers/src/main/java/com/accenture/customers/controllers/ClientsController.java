package com.accenture.customers.controllers;;
import com.accenture.customers.dto.CustomerWithAccounts;
import com.accenture.customers.services.ICustomerService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
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
    private final ICustomerService customerService;

    @GetMapping(value = "/fetchWithAccounts", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CustomerWithAccounts> fetchWithAccounts(
            @RequestParam
            @NotEmpty(message = "The field document should not be empty")
            @Size(min = 5, max = 12,message = "The document should be between 5 and 12 characteres")
            String document) {
        CustomerWithAccounts customerWithAccounts = customerService.fetchCustomersWithAccountsByDocument(document);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(customerWithAccounts);
    }
}
