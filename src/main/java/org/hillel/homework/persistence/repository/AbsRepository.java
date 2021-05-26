package org.hillel.homework.persistence.repository;

import org.hibernate.query.criteria.internal.OrderImpl;
import org.hillel.homework.persistence.entity.BaseModifyEntity;
import org.hillel.homework.service.QueryRequest;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;
import java.util.Collection;
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

    //  HQL
    public Collection<T> findAll(QueryRequest request) {
        return entityManager.createQuery("select j from " + entityClass.getName() + " j " +
                request.getOrderStr(), entityClass)
                .setFirstResult(request.getFirstResult())
                .setMaxResults(request.getPageSize())
                .getResultList();
    }

    //  вызов sql запроса
    public Collection<T> findAllAsNative(QueryRequest request) {
        return entityManager.createNativeQuery(
                "select * from " + entityClass.getAnnotation(Table.class).name() +
                        request.getOrderStr(), entityClass)
                .setFirstResult(request.getFirstResult())
                .setMaxResults(request.getPageSize())
                .getResultList();
    }

    //  вызов именованного запроса по алиасу
    public Collection<T> findAllAsNamed(QueryRequest request) {
        return entityManager.createNamedQuery(
                entityClass.getAnnotation(NamedQueries.class).value()[0].name(), entityClass)
                .setFirstResult(request.getFirstResult())
                .setMaxResults(request.getPageSize())
                .getResultList();
    }

    //  CriteriaBuilder
    public Collection<T> findAllAsCriteria(QueryRequest request) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> query = criteriaBuilder.createQuery(entityClass);
        Root<T> from = query.from(entityClass);
        Order order = new OrderImpl(from.get(request.getOrderFieldName()), request.isOrderAsc());
        return entityManager.createQuery(query.select(from).orderBy(order))
                .setFirstResult(request.getFirstResult())
                .setMaxResults(request.getPageSize())
                .getResultList();
    }

    // вызов хранимой функции
    public Collection<T> findAllAsStoredProcedure(QueryRequest request) {
        return entityManager.createStoredProcedureQuery("find_all", entityClass)
                .registerStoredProcedureParameter(1, Class.class, ParameterMode.REF_CURSOR)
                .registerStoredProcedureParameter(2, String.class, ParameterMode.IN)
                .registerStoredProcedureParameter(3, Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter(4, Integer.class, ParameterMode.IN)
                .setParameter(2, entityClass.getAnnotation(Table.class).name())
                .setParameter(3, request.getFirstResult())
                .setParameter(4, request.getPageSize())
                .getResultList();
    }
}
