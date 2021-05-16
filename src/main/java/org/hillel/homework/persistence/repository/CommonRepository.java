package org.hillel.homework.persistence.repository;

import java.util.Optional;

public interface CommonRepository<T> {
    T createOrUpdate(T entity);

    Optional<T> findById(int id);

    void removeById(int id);

    void remove(T entity);
}
