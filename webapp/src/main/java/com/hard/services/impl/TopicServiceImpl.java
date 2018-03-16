package com.hard.services.impl;

import com.hard.models.Topic;
import com.hard.repositories.TopicRepository;
import com.hard.services.TopicService;
import com.hard.specifications.Specification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class TopicServiceImpl implements TopicService {
    @Autowired
    private TopicRepository topicRepository;

    @Override
    public Collection<Topic> getAll(Specification<Topic> specification) {
        return topicRepository.getAll(specification);
    }

    @Override
    public Topic getById(long id) {
        return topicRepository.getById(id);
    }

    @Override
    public Topic save(Topic topic) {
        return topicRepository.save(topic);
    }

    @Override
    public void delete(long id) {
        topicRepository.delete(id);
    }
}
