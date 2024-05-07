package com.example.springcoffeeshop.user.service.model;

import com.example.springcoffeeshop.user.entity.RoleEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    private Long id;

    @NotNull(message = "First name cannot be null")
    @NotBlank(message = "First name is required")
    private String firstName;

    @NotNull(message = "Last name cannot be null")
    @NotBlank(message = "Last name is required")
    private String lastName;

    @Email(message = "Invalid email address")
    @NotNull(message = "Email cannot be null")
    @NotBlank(message = "Email address is required")
    private String email;

    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-zA-Z]).{6,}$", message = "Password must be in case-sensitive")
    @NotNull(message = "Password cannot be null")
    @NotBlank(message = "Password is required")
    private String password;

    @NotNull(message = "Phone number cannot be null")
    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "^\\d{10}$", message = "Invalid phone number")
    private String phone;

    @Enumerated(EnumType.STRING)
    private RoleEnum role;
}
