package com.smd.user_management.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.smd.user_management.model.User;


@SpringBootTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    // SAVE & FIND
    @Test
    void shouldSaveAndFindUser() {
        User user = new User(null, "Ahmed", "test@test.com", 25);

        User saved = userRepository.save(user);

        Optional<User> found = userRepository.findById(saved.getId());

        assertTrue(found.isPresent());
    }

    // MODEL TEST (Optional but Clean)
    @Test
    void shouldCreateUserObjectCorrectly() {
        User user = new User("1", "Ahmed", "test@test.com", 25);

        assertEquals("Ahmed", user.getName());
    }
}