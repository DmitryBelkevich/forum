package com.hard.repositories.impl;

import com.hard.models.Category;
import com.hard.repositories.CategoryRepository;
import com.hard.specifications.Specification;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
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
    public Collection<Category> getAll(Specification<Category> specification) {
        RestTemplate restTemplate = new RestTemplate();
        String url = apiUrl + "/api/categories";

        ParameterizedTypeReference typeReference = new ParameterizedTypeReference<Collection<Category>>() {
        };
        ResponseEntity<Collection<Category>> responseEntity = restTemplate.exchange(url, HttpMethod.GET, null, typeReference);

        Collection<Category> categories = responseEntity.getBody();

        return categories;
    }

    @Override
    public Category getById(long id) {
        RestTemplate restTemplate = new RestTemplate();
        String url = apiUrl + "/api/categories/" + id;

        ParameterizedTypeReference typeReference = new ParameterizedTypeReference<Category>() {
        };
        ResponseEntity<Category> responseEntity = restTemplate.exchange(url, HttpMethod.GET, null, typeReference);

        Category category = responseEntity.getBody();

        return category;
    }

    @Override
    public Category save(Category category) {
        RestTemplate restTemplate = new RestTemplate();
        String url = apiUrl + "/api/categories";

        HttpEntity<Category> httpEntity = new HttpEntity<>(category);

        ParameterizedTypeReference typeReference = new ParameterizedTypeReference<Category>() {
        };
        ResponseEntity<Category> responseEntity = restTemplate.exchange(url, HttpMethod.POST, httpEntity, typeReference);

        Category c = responseEntity.getBody();

        return c;
    }

    @Override
    public void delete(long id) {
        RestTemplate restTemplate = new RestTemplate();
        String url = apiUrl + "/api/categories/" + id;

        restTemplate.delete(url);
    }
}
