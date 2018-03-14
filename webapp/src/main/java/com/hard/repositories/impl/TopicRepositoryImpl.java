package com.hard.repositories.impl;

import com.hard.models.Topic;
import com.hard.repositories.TopicRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public class TopicRepositoryImpl implements TopicRepository {
    @Override
    public Collection<Topic> getAll() {
        return null;
    }

    @Override
    public Topic getById(long id) {
        return null;
    }

    @Override
    public Topic save(Topic model) {
        return null;
    }

    @Override
    public void delete(long id) {

    }
}
