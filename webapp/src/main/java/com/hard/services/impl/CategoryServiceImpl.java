package com.hard.services.impl;

import com.hard.models.Category;
import com.hard.repositories.CategoryRepository;
import com.hard.services.CategoryService;
import com.hard.specifications.Specification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Collection<Category> getAll(Specification<Category> specification) {
        return categoryRepository.getAll(specification);
    }

    @Override
    public Category getById(long id) {
        return categoryRepository.getById(id);
    }

    @Override
    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public void delete(long id) {
        categoryRepository.delete(id);
    }
}
