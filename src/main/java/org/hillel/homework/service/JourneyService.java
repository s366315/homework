package org.hillel.homework.service;

import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Collection;

public interface JourneyService<T> {
    Collection<T> find(String stationFrom, String stationTo, LocalDate dateFrom, LocalDate dateTo) throws SQLException;

    @Transactional
    int createJourney(T entity);
}
