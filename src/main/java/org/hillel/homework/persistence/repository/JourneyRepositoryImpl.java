package org.hillel.homework.persistence.repository;

import org.hillel.homework.persistence.entity.JourneyEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class JourneyRepositoryImpl implements JourneyRepository<JourneyEntity> {

    @PersistenceContext
    private EntityManager entityManager;

    private static final String REQUEST_SELECT_ALL = "SELECT * FROM journey;";

    @Transactional
    public int create(final JourneyEntity journeyEntity) {
        entityManager.persist(journeyEntity);
        return journeyEntity.getId();
    }

    public List<JourneyEntity> getJourneys() {
        return (List<JourneyEntity>) entityManager.createNativeQuery(REQUEST_SELECT_ALL, JourneyEntity.class).getResultList();
    }
}
