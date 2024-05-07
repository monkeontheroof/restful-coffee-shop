package com.example.springcoffeeshop.util.security.service;

import com.example.springcoffeeshop.user.entity.RoleEnum;
import com.example.springcoffeeshop.user.entity.UserEntity;
import com.example.springcoffeeshop.user.repository.UserRepository;
import com.example.springcoffeeshop.user.service.UserService;
import com.example.springcoffeeshop.user.service.mapper.UserMapper;
import com.example.springcoffeeshop.util.exception.InputValidationException;
import com.example.springcoffeeshop.util.security.resource.model.AuthenticationRequest;
import com.example.springcoffeeshop.util.security.resource.model.AuthenticationResponse;
import com.example.springcoffeeshop.util.security.resource.model.RegisterRequest;
import io.jsonwebtoken.Claims;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class AuthenticationService {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public AuthenticationResponse register(RegisterRequest request){
        var user = UserEntity.builder()
                .email(request.getEmail())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .phone(request.getPhone())
                .role(RoleEnum.USER)
                .password(request.getPassword())
                .build();
        userService.createUser(userMapper.toDto(user));
        var jwtToken = jwtService.generateToken(null,user);
        return AuthenticationResponse.builder().token(jwtToken).expiration(jwtService.extractClaim(jwtToken,Claims::getExpiration)).build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request){
//        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
//                request.getEmail(),
//                request.getPassword()
//        ));
        validateUser(request);
        var user = userService.getUserByEmail(request.getEmail().trim());
        var jwtToken = jwtService.generateToken(new HashMap<>(), userMapper.toEntity(user));
        return AuthenticationResponse.builder().token(jwtToken).expiration(jwtService.extractClaim(jwtToken, Claims::getExpiration)).build();
    }

    private void validateUser(AuthenticationRequest request) {
        if(Boolean.TRUE.equals(request.getEmail().isBlank())){
            throw new InputValidationException("Email is required");
        }
        if(Boolean.TRUE.equals(request.getPassword().isBlank())){
            throw new InputValidationException("Password is required");
        }
        UserEntity userEntity = userRepository.findByEmail(request.getEmail().trim()).orElse(null);
        if(userEntity == null || !passwordEncoder.matches(request.getPassword(), userEntity.getPassword()))
            throw new InputValidationException("Email or password is incorrect");
    }
}
