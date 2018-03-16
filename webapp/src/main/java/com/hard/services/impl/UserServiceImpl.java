package com.hard.services.impl;

import com.hard.models.User;
import com.hard.repositories.UserRepository;
import com.hard.services.UserService;
import com.hard.specifications.Specification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public Collection<User> getAll(Specification<User> specification) {
        return userRepository.getAll(specification);
    }

    @Override
    public User getById(long id) {
        return userRepository.getById(id);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public void delete(long id) {
        userRepository.delete(id);
    }
}
