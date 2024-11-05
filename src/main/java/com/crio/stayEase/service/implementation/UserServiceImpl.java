package com.crio.stayEase.service.implementation;

import com.crio.stayEase.constants.Role;
import com.crio.stayEase.dto.CreateUser;
import com.crio.stayEase.entity.User;
import com.crio.stayEase.exception.RoleNotFoundException;
import com.crio.stayEase.exception.UserEmailAlreadyExistsException;
import com.crio.stayEase.exception.UserNotFoundException;
import com.crio.stayEase.repository.UserRepository;
import com.crio.stayEase.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public User registerUser(CreateUser createUser) {
        log.info("Entered registerUser() method - createUser email: {}", createUser.getEmail());
        try {
            User user = createUserObj(createUser);

            return userRepository.save(user);
        } catch (DataIntegrityViolationException ex) {
            log.error("User with email: {} already exists", createUser.getEmail());
            throw new UserEmailAlreadyExistsException("Email already in use by another user. Please try with a different email");
        }
    }

    private User createUserObj(CreateUser createUser) {
        log.info("Entered createUserObj() method - createUser email: {}", createUser.getEmail());
        User user = new User();

        user.setFirstName(createUser.getFirstName());
        user.setLastName(createUser.getLastName());
        user.setEmail(createUser.getEmail());
        user.setPassword(passwordEncoder.encode(createUser.getPassword()));
        setUserRole(user, createUser.getRole());

        return user;
    }

    private void setUserRole(User user, String role) {
        log.info("Entered setUserRole() method - user email: {}, role: {}", user.getEmail(), role);
        if (role == null || role.equalsIgnoreCase(Role.CUSTOMER.toString())) {
            user.setRole(Role.CUSTOMER);
        } else if (role.equalsIgnoreCase(Role.HOTEL_MANAGER.toString())) {
            user.setRole(Role.HOTEL_MANAGER);
        } else if (role.equalsIgnoreCase(Role.ADMIN.toString())) {
            user.setRole(Role.ADMIN);
        } else {
            throw new RoleNotFoundException("Invalid role: " + role + " is not a valid role.");
        }
    }

    @Override
    public User findByEmail(String email) {
        log.info("Entered findByEmail() method - email: {}", email);
        return userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new UserNotFoundException("User with email " + email + " does not exist."));
    }

    @Override
    public User saveUser(User user) {
        log.info("Entered saveUser() method - user email: {}", user.getEmail());
        if (user == null) {
            throw new UserNotFoundException("User object is null.");
        }

        return userRepository.save(user);
    }
}
