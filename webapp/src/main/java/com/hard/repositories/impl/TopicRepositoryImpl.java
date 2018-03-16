package com.hard.repositories.impl;

import com.hard.models.Topic;
import com.hard.repositories.TopicRepository;
import com.hard.specifications.Specification;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;

@Repository
public class TopicRepositoryImpl implements TopicRepository {
    @Value("${api.url}")
    private String apiUrl;

    @Override
    public Collection<Topic> getAll(Specification<Topic> specification) {
        RestTemplate restTemplate = new RestTemplate();
        String url = apiUrl + "/api/topics?" + specification.getRequest();

        ParameterizedTypeReference typeReference = new ParameterizedTypeReference<Collection<Topic>>() {
        };
        ResponseEntity<Collection<Topic>> responseEntity = restTemplate.exchange(url, HttpMethod.GET, null, typeReference);

        HttpStatus statusCode = responseEntity.getStatusCode();
        MediaType contentType = responseEntity.getHeaders().getContentType();
        Collection<Topic> topics = responseEntity.getBody();

        return topics;
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
