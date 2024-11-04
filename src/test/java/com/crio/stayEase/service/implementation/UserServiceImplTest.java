package com.crio.stayEase.service.implementation;

import com.crio.stayEase.constants.Role;
import com.crio.stayEase.dto.CreateUser;
import com.crio.stayEase.entity.User;
import com.crio.stayEase.exception.RoleNotFoundException;
import com.crio.stayEase.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private BCryptPasswordEncoder passwordEncoder;

    @InjectMocks
    private UserServiceImpl userService;

    private CreateUser createUser;
    private User user;

    @BeforeEach
    void setUp() {
        createUser = new CreateUser("John", "Doe", "john@example.com", "password123", "CUSTOMER");
        user = new User();
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setEmail("john@example.com");
        user.setPassword("encodedPassword");
        user.setRole(Role.CUSTOMER);
    }

    @Test
    void registerUser_ShouldSaveUser_WhenValidCreateUserIsProvided() {
        when(passwordEncoder.encode(createUser.getPassword())).thenReturn("encodedPassword");
        when(userRepository.save(any(User.class))).thenReturn(user);

        User savedUser = userService.registerUser(createUser);

        assertNotNull(savedUser);
        assertEquals("John", savedUser.getFirstName());
        assertEquals("Doe", savedUser.getLastName());
        assertEquals("john@example.com", savedUser.getEmail());
        assertEquals("encodedPassword", savedUser.getPassword());
        assertEquals(Role.CUSTOMER, savedUser.getRole());

        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void registerUser_ShouldThrowRoleNotFoundException_WhenInvalidRoleIsProvided() {
        createUser.setRole("INVALID_ROLE");

        assertThrows(RoleNotFoundException.class, () -> userService.registerUser(createUser));
    }
}