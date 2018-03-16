package com.hard.services.impl;

import com.hard.models.Message;
import com.hard.repositories.MessageRepository;
import com.hard.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Service
@Transactional
public class MessageServiceImpl implements MessageService {
    @Autowired
    private MessageRepository messageRepository;

    @Override
    public Collection<Message> getAll(Specification<Message> specification) {
        return messageRepository.findAll(specification);
    }

    @Override
    public Message getById(long id) {
        return messageRepository.findOne(id);
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
