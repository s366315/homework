package org.hillel.homework.persistence.repository;

import org.hillel.homework.persistence.entity.JourneyEntity;
import org.hillel.homework.persistence.entity.StopEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class StopRepositoryImpl implements StopRepository<StopEntity> {

    @PersistenceContext
    private EntityManager entityManager;

    private static final String REQUEST_SELECT_ALL = "SELECT * FROM stop;";

    @Transactional
    public int create(final StopEntity journeyEntity) {
        entityManager.persist(journeyEntity);
        return journeyEntity.getId();
    }

    public List<StopEntity> getStops() {
        return (List<StopEntity>) entityManager.createNativeQuery(REQUEST_SELECT_ALL, JourneyEntity.class).getResultList();
    }
}
