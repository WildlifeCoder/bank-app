package com.accenture.customers.controllers;

import com.accenture.customers.dto.CustomerDto;
import com.accenture.customers.dto.ErrorResponseDto;
import com.accenture.customers.dto.ResponseDto;

import com.accenture.customers.dto.SupportInfoDto;
import com.accenture.customers.services.ICustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

import jakarta.validation.constraints.Size;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Tag(name = "REST to handle clients", description = "CRUD REST to customers")
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api")
public class CustomerController {

    @NonNull()
    private final ICustomerService customerService;

    @Value("${build.version}")
    private String buildVersion;

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

    @GetMapping(value = "/build-info",  produces = {
            MediaType.APPLICATION_JSON_VALUE
    })
    public ResponseEntity<ResponseDto> getBuildInfo() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDto("200", buildVersion ));
    }

    @GetMapping(value = "/customer",  produces = {
            MediaType.TEXT_PLAIN_VALUE
    })
    public String sayHelloFromController() {
        return "Hi From microservice customer";
    }


    @GetMapping(value = "/date",  produces = {
            MediaType.TEXT_PLAIN_VALUE
    })
    public String dateTimeController() {
        return LocalDateTime.now().toString();
    }


    @Operation(
            summary = "Creation of clients",
            description = "Handle the creation of clients in our bank system"
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
    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDto> createCustomer(@Valid @RequestBody CustomerDto customerDto) {
        customerService.createCustomer(customerDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto("201", "Cliente creado exitosamente" ));
    }




    @Operation(
            summary = "Request to get client by document",
            description = "Handle the request of the existent client by document in the bank system"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP STATUS OK",
                    content = @Content(
                            schema = @Schema(implementation = CustomerDto.class)
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
    @GetMapping(value = "/fetchByDocument", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CustomerDto> fetchByDocument(
            @RequestParam
            @NotEmpty(message = "The field document should not be empty")
            @Size(min = 5, max = 12,message = "The document should be between 5 and 12 characteres")
            String document) {
        CustomerDto customerDto = customerService.fetchCustomerByDocument(document);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(customerDto);
    }

    @Operation(
            summary = "Request to get client by email",
            description = "Handle the request of the existent client by email in the bank system"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP STATUS OK",
                    content = @Content(
                            schema = @Schema(implementation = CustomerDto.class)
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
    @GetMapping(value = "/fetchByEmail", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CustomerDto> fetchByEmail(
            @RequestParam
            @NotEmpty(message = "The field email should not be empty")
            @Email(message = "The field email should be a valid email")
            String email) {
        CustomerDto customerDto = customerService.fetchCustomerByEmail(email);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(customerDto);
    }



    @Operation(
            summary = "Get all clients",
            description = "Handle the requets to get all clients in our bank system"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP STATUS OK",
                    content = @Content(
                    schema = @Schema(implementation = CustomerDto.class)
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
    @GetMapping(value = "/fetchCustomers", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CustomerDto>> fetchAllCustomers() {
       List<CustomerDto> customers = customerService.fetchAllCustomers();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(customers);
    }


    @Operation(
            summary = "Modification of client",
            description = "Handle the modificaction of the selected client bank system"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP STATUS OK"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "HTTP STATUS NOT FOUND",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "HTTP STATUS BAD REQUEST",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })
    @PutMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CustomerDto> updateCustomer(@Valid @RequestBody CustomerDto customer) {
        CustomerDto updatedCustomer = customerService.updateCustomer(customer);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(updatedCustomer);
    }


    @Operation(
            summary = "Delete client",
            description = "Handle the erase of the client by document in our bank system"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP STATUS OK"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "HTTP STATUS NOT FOUND",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })
    @DeleteMapping(value = "/deleteByDocument/{document}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDto> deleteByDocument(
            @PathVariable
            @NotEmpty(message = "The field document should not be empty")
            @Size(min = 5, max = 12,message = "The document should be between 5 and 12 characteres")
            String document) {
        customerService.deleteByDocument(document);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDto("200", "Cliente eliminado exitosamente" ));
    }

    @DeleteMapping(value = "/deleteByEmail/{email}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDto> deleteByEmail(
            @PathVariable
            @NotEmpty(message = "The field email should not be empty")
            @Email(message = "The field email should be a valid email")
            String email) {
        customerService.deleteByEmail(email);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDto("200", "Cliente eliminado exitosamente" ));
    }
}
