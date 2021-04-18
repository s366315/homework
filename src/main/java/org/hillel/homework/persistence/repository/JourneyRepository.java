package org.hillel.homework.persistence.repository;

import java.util.List;

public interface JourneyRepository<T> {
    List<T> getJourneys();

    int create(T entity);
}
