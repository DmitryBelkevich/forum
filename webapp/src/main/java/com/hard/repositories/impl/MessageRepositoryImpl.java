package com.hard.repositories.impl;

import com.hard.models.Message;
import com.hard.repositories.MessageRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public class MessageRepositoryImpl implements MessageRepository {
    @Override
    public Collection<Message> getAll() {
        return null;
    }

    @Override
    public Message getById(long id) {
        return null;
    }

    @Override
    public Message save(Message model) {
        return null;
    }

    @Override
    public void delete(long id) {

    }
}