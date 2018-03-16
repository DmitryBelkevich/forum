package com.hard.repositories.impl;

import com.hard.models.User;
import com.hard.repositories.UserRepository;
import com.hard.specifications.Specification;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public class UserRepositoryImpl implements UserRepository {
    @Override
    public Collection<User> getAll(Specification<User> specification) {
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
