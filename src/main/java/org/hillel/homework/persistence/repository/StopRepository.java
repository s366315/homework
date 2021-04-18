package org.hillel.homework.persistence.repository;

import java.util.List;

public interface StopRepository<T> {
    List<T> getStops();

    int create(T entity);
}
