package org.hillel.homework.persistence.repository;

import org.hillel.homework.persistence.entity.BaseModifyEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

import javax.persistence.*;
import javax.persistence.criteria.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Objects;

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

    //  HQL
    public Collection<T> findAll(){
        return entityManager.createQuery("select j from " + entityClass.getName() + " j ", entityClass).getResultList();
    }

    //  вызов sql запроса
    public Collection<T> findAllAsNative(){
        return entityManager.createNativeQuery(
                "select * from " + entityClass.getAnnotation(Table.class).name(), entityClass).getResultList();
    }

    //  вызов именованного запроса по алиасу
    public Collection<T> findAllAsNamed(){
        return entityManager.createNamedQuery(entityClass.getAnnotation(NamedQueries.class).value()[0].name(), entityClass).getResultList();
    }

    //  CriteriaBuilder
    public Collection<T> findAllAsCriteria(){
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> query = criteriaBuilder.createQuery(entityClass);
        Root<T> from = query.from(entityClass);
        return entityManager.createQuery(query.select(from)).getResultList();
    }

    // вызов хранимой функции
    public Collection<T> findAllAsStoredProcedure(){
        return entityManager.createNamedStoredProcedureQuery("findAllVehicles").getResultList();
    }
}
