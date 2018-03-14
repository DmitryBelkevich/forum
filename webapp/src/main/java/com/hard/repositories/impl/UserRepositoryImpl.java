package com.hard.repositories.impl;

import com.hard.models.User;
import com.hard.repositories.UserRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public class UserRepositoryImpl implements UserRepository {
    @Override
    public Collection<User> getAll() {
        return null;
    }

    @Override
    public User getById(long id) {
        return null;
    }

    @Override
    public User save(User model) {
        return null;
    }

    @Override
    public void delete(long id) {

    }
}
