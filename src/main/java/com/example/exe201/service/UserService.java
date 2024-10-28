package com.example.exe201.service;

import com.example.exe201.model.User;
import com.example.exe201.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface UserService {
    User createUser(User user);

    List<User> getUsers();

    User getUserById(Long userId);

    User updateUser(Long userId, User user);
    User findUserByUsername(String username);
}
