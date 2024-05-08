package com.example.springcoffeeshop.user.resource;

import com.example.springcoffeeshop.user.service.UserService;
import com.example.springcoffeeshop.user.service.model.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Profile")
@RequestMapping("/v1/profile")
public class ProfileResource {

    @Autowired
    private UserService userService;

    @GetMapping
    @Operation(
            description = "Get user profile",
            summary = "Get user information",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Get user information successfully")
            }
    )
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<User> getProfile(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return ResponseEntity.ok(userService.getProfile(authentication));
    }
}
