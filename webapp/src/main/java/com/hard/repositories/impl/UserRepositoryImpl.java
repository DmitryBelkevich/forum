package com.hard.repositories.impl;

import com.hard.models.User;
import com.hard.repositories.UserRepository;
import com.hard.specifications.Specification;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;

@Repository
public class UserRepositoryImpl implements UserRepository {
    @Value("${api.url}")
    private String apiUrl;

    @Override
    public Collection<User> getAll(Specification<User> specification) {
        RestTemplate restTemplate = new RestTemplate();
        String url = apiUrl + "/api/users";

        ParameterizedTypeReference typeReference = new ParameterizedTypeReference<Collection<User>>() {
        };
        ResponseEntity<Collection<User>> responseEntity = restTemplate.exchange(url, HttpMethod.GET, null, typeReference);

        Collection<User> users = responseEntity.getBody();

        return users;
    }

    @Override
    public User getById(long id) {
        RestTemplate restTemplate = new RestTemplate();
        String url = apiUrl + "/api/users/" + id;

        ParameterizedTypeReference typeReference = new ParameterizedTypeReference<User>() {
        };
        ResponseEntity<User> responseEntity = restTemplate.exchange(url, HttpMethod.GET, null, typeReference);

        User user = responseEntity.getBody();

        return user;
    }

    @Override
    public User save(User model) {
        return null;
    }

    @Override
    public void delete(long id) {

    }
}
