package com.crio.stayEase.service;

import com.crio.stayEase.constants.Role;
import com.crio.stayEase.dto.CreateUser;
import com.crio.stayEase.entity.User;
import com.crio.stayEase.exception.RoleNotFoundException;
import com.crio.stayEase.exception.UserNotFoundException;
import com.crio.stayEase.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public User registerUser(CreateUser createUser) {
        User user = createUserObj(createUser);

        return userRepository.save(user);
    }

    private User createUserObj(CreateUser createUser) {
        User user = new User();

        user.setFirstName(createUser.getFirstName());
        user.setLastName(createUser.getLastName());
        user.setEmail(createUser.getEmail());
        user.setPassword(passwordEncoder.encode(createUser.getPassword()));
        setUserRole(user, createUser.getRole());

        return user;
    }

    private void setUserRole(User user, String role) {
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
        return userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new UserNotFoundException("User with email " + email + " does not exist."));
    }

    @Override
    public User saveUser(User user) {
        if (user == null) {
            throw new UserNotFoundException("User object is null.");
        }

        return userRepository.save(user);
    }
}
