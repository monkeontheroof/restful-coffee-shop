package com.example.springcoffeeshop.user.service;

import com.example.springcoffeeshop.order.entity.OrderEntity;
import com.example.springcoffeeshop.order.entity.OrderStatus;
import com.example.springcoffeeshop.order.repository.OrderRepository;
import com.example.springcoffeeshop.user.entity.UserEntity;
import com.example.springcoffeeshop.user.repository.UserRepository;
import com.example.springcoffeeshop.user.service.mapper.UserMapper;
import com.example.springcoffeeshop.user.service.model.User;
import com.example.springcoffeeshop.util.exception.InputValidationException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<User> getAllUsers() {
        return userMapper.toDtos(userRepository.findAll());
    }

    public User getUserById(Long id) {
        return userMapper.toDto(userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found")));
    }

    public User getUserByEmail(String email) {
        return userMapper.toDto(userRepository.findByEmail(email).orElseThrow(() -> new EntityNotFoundException("User not found")));
    }

    public User createUser(User user) {
        validateUser(user);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        UserEntity createdUser = userRepository.save(userMapper.toEntity(user));

        OrderEntity orderEntity = OrderEntity.builder()
//                .amount(0.0)
//                .totalAmount(0.0)
//                .discount(0L)
//                .payment("")
//                .description("")
                .userEntity(createdUser)
                .status(OrderStatus.PENDING)
                .address("")
                .id(UUID.randomUUID())
                .build();
        orderRepository.save(orderEntity);
        return userMapper.toDto(createdUser);
    }

    public User updateUserById(Long id, User user) {
        UserEntity updatedUser = userMapper.toEntity(getUserById(id));
        if(user.getEmail().equals(updatedUser.getEmail()) && userRepository.existsByEmail(user.getEmail().trim())){
            throw new InputValidationException("Email already exists");
        }
        updateUser(user, updatedUser);
        return userMapper.toDto(updatedUser);
    }

    public Optional<String> getCurrentUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return Optional.of(authentication.getName());
    }

    public void deleteUser(Long id) {
        if(getUserById(id) != null)
            userRepository.deleteById(id);
    }

    private void validateUser(User user) {
        if (userRepository.existsByEmail(user.getEmail().trim())) {
            throw new InputValidationException("Email already exists");
        }
        if (userRepository.existsByPhone(user.getPhone().trim())) {
            throw new InputValidationException("Phone number already exists");
        }
        if(user.getPassword() == null){
            throw new InputValidationException("Password cannot be null");
        }
        if(user.getPassword().isBlank()){
            throw new InputValidationException("Password cannot be empty");
        }
        if(user.getPassword().length() < 8){
            throw new InputValidationException("Password must be at least 8 characters long");
        }
    }

    private void updateUser(User user, UserEntity updatedUser) {
        updatedUser.setEmail(user.getEmail());
        updatedUser.setPassword(user.getPassword());
        updatedUser.setPhone(user.getPhone());
        updatedUser.setRole(user.getRole());
        updatedUser.setFirstName(user.getFirstName());
        updatedUser.setLastName(user.getLastName());
    }
}
