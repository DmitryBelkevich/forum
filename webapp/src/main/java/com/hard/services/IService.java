package com.hard.services;

import java.util.Collection;

public interface IService<T> {
    Collection<T> getAll();

    T getById(long id);

    T save(T model);

    void delete(long id);
}
