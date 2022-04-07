package com.kodilla.ecommercee.service;


import com.kodilla.ecommercee.UserNotFoundException;
import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Random;


@Service
public class UserService {


    private UserRepository userRepository;

    public User createUser(final User user) {
        return userRepository.save(user);
    }

    public User blockUserId(final Long userId) throws UserNotFoundException {
        User blockUser = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        blockUser.setActive(false);
        return userRepository.save(blockUser);
    }

    public User generateKey(final User user) {
        Random random = new Random();
        String tokenUserKey = String.valueOf(random.nextInt(99999999));
        user.setPersonalKey(tokenUserKey);
        return userRepository.save(user);
    }

}