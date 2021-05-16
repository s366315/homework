package org.hillel.homework.persistence.repository;

import org.hillel.homework.persistence.entity.BaseModifyEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

public abstract class AbsRepository<T extends BaseModifyEntity> implements CommonRepository<T> {

    @PersistenceContext
    EntityManager entityManager;

    private final Class<T> entityClass;

    public AbsRepository(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    @Override
    public T createOrUpdate(T entity) {
        if (entity.getId() == null) {
            entityManager.persist(entity);
        } else {
            return entityManager.merge(entity);
        }
        return entity;
    }

    @Override
    public Optional<T> findById(int id) {
        return Optional.empty();
    }

    @Override
    public void removeById(int id) {
        entityManager.remove(entityManager.getReference(entityClass, id));
    }

    @Override
    public void remove(T entity) {

    }
}
