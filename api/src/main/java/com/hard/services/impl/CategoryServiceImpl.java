package com.hard.services.impl;

import com.hard.models.Category;
import com.hard.repositories.CategoryRepository;
import com.hard.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Collection<Category> getAll(Specification<Category> specification) {
        return categoryRepository.findAll(specification);
    }

    @Override
    public Category getById(long id) {
        return categoryRepository.findOne(id);
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
