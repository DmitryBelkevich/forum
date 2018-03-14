package com.hard.repositories.impl;

import com.hard.models.Category;
import com.hard.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;

@Repository
@PropertySource("classpath:application.properties")
public class CategoryRepositoryImpl implements CategoryRepository {
    @Value("${api.url}")
    private String apiUrl;

    @Override
    public Collection<Category> getAll() {
        RestTemplate restTemplate = new RestTemplate();
        String url = apiUrl + "/api/categories";

        ParameterizedTypeReference typeReference = new ParameterizedTypeReference<Collection<Category>>() {
        };
        ResponseEntity<Collection<Category>> responseEntity = restTemplate.exchange(url, HttpMethod.GET, null, typeReference);

        HttpStatus statusCode = responseEntity.getStatusCode();
        MediaType contentType = responseEntity.getHeaders().getContentType();
        Collection<Category> categories = responseEntity.getBody();

        return categories;
    }

    @Override
    public Category getById(long id) {
        return null;
    }

    @Override
    public Category save(Category model) {
        return null;
    }

    @Override
    public void delete(long id) {

    }
}
