package com.curequest.service;

import com.curequest.entity.User;
import com.curequest.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User save(User u) { return userRepository.save(u); }
    public User findByUsername(String username) { return userRepository.findByUsername(username); }
}