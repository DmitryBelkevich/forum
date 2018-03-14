package com.hard.repositories;

import java.util.Collection;

public interface IRepository<T> {
    Collection<T> getAll();

    T getById(long id);

    T save(T model);

    void delete(long id);
}
