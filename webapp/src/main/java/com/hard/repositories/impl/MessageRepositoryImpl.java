package com.hard.repositories.impl;

import com.hard.models.Message;
import com.hard.repositories.MessageRepository;
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
public class MessageRepositoryImpl implements MessageRepository {
    @Value("${api.url}")
    private String apiUrl;

    @Override
    public Collection<Message> getAll(Specification<Message> specification) {
        RestTemplate restTemplate = new RestTemplate();
        String url = apiUrl + "/api/messages";

        ParameterizedTypeReference typeReference = new ParameterizedTypeReference<Collection<Message>>() {
        };
        ResponseEntity<Collection<Message>> responseEntity = restTemplate.exchange(url, HttpMethod.GET, null, typeReference);

        Collection<Message> messages = responseEntity.getBody();

        return messages;
    }

    @Override
    public Message getById(long id) {
        RestTemplate restTemplate = new RestTemplate();
        String url = apiUrl + "/api/messages/" + id;

        ParameterizedTypeReference typeReference = new ParameterizedTypeReference<Message>() {
        };
        ResponseEntity<Message> responseEntity = restTemplate.exchange(url, HttpMethod.GET, null, typeReference);

        Message message = responseEntity.getBody();

        return message;
    }

    @Override
    public Message save(Message message) {
        RestTemplate restTemplate = new RestTemplate();
        String url = apiUrl + "/api/messages";

        HttpEntity<Message> httpEntity = new HttpEntity<>(message);

        ParameterizedTypeReference typeReference = new ParameterizedTypeReference<Message>() {
        };
        ResponseEntity<Message> responseEntity = restTemplate.exchange(url, HttpMethod.POST, httpEntity, typeReference);

        Message m = responseEntity.getBody();

        return m;
    }

    @Override
    public void delete(long id) {
        RestTemplate restTemplate = new RestTemplate();
        String url = apiUrl + "/api/messages/" + id;

        restTemplate.delete(url);
    }
}
