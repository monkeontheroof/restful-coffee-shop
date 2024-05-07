package com.example.springcoffeeshop.user.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class UserEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @Column(unique = true)
    private String email;

    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-zA-Z]).{6,}$", message = "Password must be in case-sensitive")
    @NotNull(message = "Password cannot be null")
    @NotBlank(message = "Password is required")
    private String password;

    @NotNull(message = "Phone number cannot be null")
    @NotBlank(message = "Phone number is required")
    @Column(unique = true)
    @Pattern(regexp = "^\\d{10}$", message = "Invalid phone number")
    private String phone;

    @Enumerated(EnumType.STRING)
    @Column
    private RoleEnum role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
