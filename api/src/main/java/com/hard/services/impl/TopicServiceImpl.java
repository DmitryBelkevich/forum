package com.hard.services.impl;

import com.hard.models.Topic;
import com.hard.repositories.TopicRepository;
import com.hard.services.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Service
@Transactional
public class TopicServiceImpl implements TopicService {
    @Autowired
    private TopicRepository topicRepository;

    @Override
    public Collection<Topic> getAll(Specification<Topic> specification) {
        return topicRepository.findAll(specification);
    }

    @Override
    public Topic getById(long id) {
        return topicRepository.findOne(id);
    }

    @Override
    public Topic save(Topic topic) {
        return topicRepository.save(topic);
    }

    @Override
    public void delete(long id) {
        topicRepository.delete(id);
    }

    @Override
    public void deleteAll() {
        topicRepository.deleteAll();
    }
}
