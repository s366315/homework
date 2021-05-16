package org.hillel.homework.persistence.repository;

import java.util.Optional;

public interface CommonRepository<T> {
    T createOrUpdate(T entity);

    Optional<T> findById(Integer id);
}
