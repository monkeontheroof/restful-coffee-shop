package com.example.springcoffeeshop.util.security.resource.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationRequest {
    @NotNull(message = "Email cannot be null")
    @NotBlank(message = "Email is required")
    private String email;

    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-zA-Z]).{6,}$", message = "Password must be in case-sensitive")
    @NotNull(message = "Password cannot be null")
    @NotBlank(message = "Password is required")
    private String password;
}
