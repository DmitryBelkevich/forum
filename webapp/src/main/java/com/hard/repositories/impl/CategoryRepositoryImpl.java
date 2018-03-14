package com.hard.repositories.impl;

import com.hard.models.Category;
import com.hard.repositories.CategoryRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public class CategoryRepositoryImpl implements CategoryRepository {
    @Override
    public Collection<Category> getAll() {
        return null;
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
