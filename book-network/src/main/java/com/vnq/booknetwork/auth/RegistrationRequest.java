package com.vnq.booknetwork.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RegistrationRequest {
    @Email(message = "Email should be valid")
    @NotEmpty(message = "Email is required")
    @NotBlank(message = "Email is required")
    private String email;
    @Size(min = 5, message = "Password should be at least 5 characters")
    @NotEmpty(message = "Password is required")
    @NotBlank(message = "Password is required")
    private String password;
    @NotEmpty(message = "First name is required")
    @NotBlank(message = "First name is required")
    private String firstName;
    @NotEmpty(message = "Last name is required")
    @NotBlank(message = "Last name is required")
    private String lastName;

}
