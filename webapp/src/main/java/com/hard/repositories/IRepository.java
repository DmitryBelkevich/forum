package com.hard.repositories;

import com.hard.specifications.Specification;

import java.util.Collection;

public interface IRepository<T> {
    Collection<T> getAll(Specification<T> specification);

    T getById(long id);

    T save(T model);

    void delete(long id);
}
