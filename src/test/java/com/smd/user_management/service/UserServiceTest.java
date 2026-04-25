package com.smd.user_management.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.smd.user_management.model.User;
import com.smd.user_management.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    private User user;

    @BeforeEach
    void setUp() {
        user = new User("1", "Ahmed", "ahmed@test.com", 25);
    }

    // CREATE USER
    @Test
    void shouldCreateUserSuccessfully() {
        when(userRepository.save(any(User.class))).thenReturn(user);

        User saved = userService.createUser(user);

        assertNotNull(saved);
        assertEquals("Ahmed", saved.getName());
        verify(userRepository, times(1)).save(user);
    }

    // GET USER BY ID
    @Test
    void shouldReturnUserWhenIdExists() {
        when(userRepository.findById("1")).thenReturn(Optional.of(user));

        User found = userService.getUserById("1");

        assertEquals("Ahmed", found.getName());
    }

    // NOT FOUND CASE
    @Test
    void shouldThrowExceptionWhenUserNotFound() {
        when(userRepository.findById("1")).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> {
            userService.getUserById("1");
        });
    }

    // UPDATE USER
    @Test
    void shouldUpdateUserSuccessfully() {
        when(userRepository.findById("1")).thenReturn(Optional.of(user));
        when(userRepository.save(any(User.class))).thenReturn(user);

        user.setName("Updated");

        User updated = userService.updateUser("1", user);

        assertEquals("Updated", updated.getName());
    }
    
    // DELETE USER
    @Test
    void shouldDeleteUserSuccessfully() {
        when(userRepository.existsById("1")).thenReturn(true);

        userService.deleteUser("1");

        verify(userRepository).deleteById("1");
    }

    // DELETE NOT FOUND
    @Test
    void shouldThrowWhenDeletingNonExistingUser() {
        when(userRepository.existsById("1")).thenReturn(false);

        assertThrows(RuntimeException.class, () -> {
            userService.deleteUser("1");
        });
    }

    // GET ALL USERS
    @Test
    void shouldReturnAllUsers() {
        when(userRepository.findAll()).thenReturn(List.of(user));

        List<User> users = userService.getAllUsers();

        assertEquals(1, users.size());
    }
}
