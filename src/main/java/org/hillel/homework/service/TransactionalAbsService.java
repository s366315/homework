package org.hillel.homework.service;

import org.hillel.homework.persistence.entity.BaseModifyEntity;
import org.hillel.homework.persistence.repository.AbsRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

public abstract class TransactionalAbsService<T extends BaseModifyEntity> {

    private final AbsRepository<T> repository;

    public TransactionalAbsService(AbsRepository<T> repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public Collection<T> findAll(QueryRequest request) {
        switch (request.getQueryType()) {
            case HQL:
                return findAllHql(request);
            case NATIVE:
                return findAllAsNative(request);
            case NAMED:
                return findAllAsNamed(request);
            case CRITERIA:
                return findAllAsCriteria(request);
            case STORED_PROCEDURE:
                return findAllAsStoredProcedure(request);
            default:
                throw new IllegalArgumentException("unknown queryType");
        }
    }

    @Transactional(readOnly = true)
    public Collection<T> findAllHql(QueryRequest request) {
        return repository.findAll(request);
    }

    @Transactional(readOnly = true)
    public Collection<T> findAllAsNative(QueryRequest request) {
        return repository.findAllAsNative(request);
    }

    @Transactional(readOnly = true)
    public Collection<T> findAllAsNamed(QueryRequest request) {
        return repository.findAllAsNamed(request);
    }

    @Transactional(readOnly = true)
    public Collection<T> findAllAsCriteria(QueryRequest request) {
        return repository.findAllAsCriteria(request);
    }

    @Transactional(readOnly = true)
    public Collection<T> findAllAsStoredProcedure(QueryRequest request) {
        return repository.findAllAsStoredProcedure(request);
    }
}
