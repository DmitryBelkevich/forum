package com.hard.services.impl;

import com.hard.models.Message;
import com.hard.repositories.MessageRepository;
import com.hard.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Service
@Transactional
public class MessageServiceImpl implements MessageService {
    @Autowired
    private MessageRepository messageRepository;

    @Override
    public Collection<Message> getAll() {
        return messageRepository.findAll();
    }

    @Override
    public Message getById(long id) {
        return messageRepository.findOne(id);
    }

    @Override
    public Message save(Message model) {
        return messageRepository.save(model);
    }

    @Override
    public void delete(long id) {
        messageRepository.delete(id);
    }
}
