package com.hard.repositories.impl;

import com.hard.models.Topic;
import com.hard.repositories.TopicRepository;
import com.hard.specifications.Specification;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
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

        String url = apiUrl + "/api/topics";

        if (specification != null)
            url += "?" + specification.getRequest();

        ParameterizedTypeReference typeReference = new ParameterizedTypeReference<Collection<Topic>>() {
        };
        ResponseEntity<Collection<Topic>> responseEntity = restTemplate.exchange(url, HttpMethod.GET, null, typeReference);

        Collection<Topic> topics = responseEntity.getBody();

        return topics;
    }

    @Override
    public Topic getById(long id) {
        RestTemplate restTemplate = new RestTemplate();
        String url = apiUrl + "/api/topics/" + id;

        ParameterizedTypeReference typeReference = new ParameterizedTypeReference<Topic>() {
        };
        ResponseEntity<Topic> responseEntity = restTemplate.exchange(url, HttpMethod.GET, null, typeReference);

        Topic topic = responseEntity.getBody();

        return topic;
    }

    @Override
    public Topic save(Topic topic) {
        RestTemplate restTemplate = new RestTemplate();
        String url = apiUrl + "/api/topics";

        HttpEntity<Topic> httpEntity = new HttpEntity<>(topic);

        ParameterizedTypeReference typeReference = new ParameterizedTypeReference<Topic>() {
        };
        ResponseEntity<Topic> responseEntity = restTemplate.exchange(url, HttpMethod.POST, httpEntity, typeReference);

        Topic t = responseEntity.getBody();

        return t;
    }

    @Override
    public void delete(long id) {
        RestTemplate restTemplate = new RestTemplate();
        String url = apiUrl + "/api/topics/" + id;

        restTemplate.delete(url);
    }
}
