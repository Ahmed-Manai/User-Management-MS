package com.smd.user_management.service;

import com.smd.user_management.model.User;
import java.util.List;

public interface UserService {

    User createUser(User user);

    List<User> getAllUsers();

    User getUserById(String id);

    User updateUser(String id, User user);

    void deleteUser(String id);
}