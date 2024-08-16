package com.accenture.accounts.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(
        name = "Customer",
        description = "Schema to storage the clients data"
)
public class CustomerDto {

    @Schema(
            description = "Document number of the client"
    )
    @NotEmpty(message = "The field document should not be empty")
    @Size(min = 5, max = 12,message = "The document should be between 5 and 12 characteres")
    private String document;

    @Schema(
            description = "Name of the client"
    )
    @NotEmpty(message = "The field name should not be empty")
    @Size(min = 2, max = 80,message = "The name should be between 2 and 80 characteres")
    private String name;

    @Schema(
            description = "Email of the client"
    )
    @NotEmpty(message = "The field email should not be empty")
    @Email(message = "The field email should be a valid email")
    private String email;

    @Schema(
            description = "Phone of the client"
    )
    @NotEmpty(message = "The field phone number should not be empty")
    @Pattern(regexp = "(^$|\\d{10})", message = "The phone number should have 10 digits")
    private String phone;

    @Schema(
            description = "Address of the client"
    )
    @Size(min = 20, max = 150,message = "The address should be between 20 and 150 characteres")
    private String address;



}
