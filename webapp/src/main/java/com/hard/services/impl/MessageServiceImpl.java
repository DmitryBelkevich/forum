package com.hard.services.impl;

import com.hard.models.Message;
import com.hard.repositories.MessageRepository;
import com.hard.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    private MessageRepository messageRepository;

    @Override
    public Collection<Message> getAll() {
        return messageRepository.getAll();
    }

    @Override
    public Message getById(long id) {
        return messageRepository.getById(id);
    }

    @Override
    public Message save(Message message) {
        return messageRepository.save(message);
    }

    @Override
    public void delete(long id) {
        messageRepository.delete(id);
    }
}
