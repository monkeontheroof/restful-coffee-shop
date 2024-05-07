package com.example.springcoffeeshop.util.security.resource;

import com.example.springcoffeeshop.util.security.resource.model.AuthenticationRequest;
import com.example.springcoffeeshop.util.security.resource.model.AuthenticationResponse;
import com.example.springcoffeeshop.util.security.resource.model.RegisterRequest;
import com.example.springcoffeeshop.util.security.service.AuthenticationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/v1/auth")
public class AuthenticationResource {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@Valid @RequestBody RegisterRequest request){
        return ResponseEntity.ok(authenticationService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> authenticate(@Valid @RequestBody AuthenticationRequest request){
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }
}
