package org.hillel.homework.service;

import org.hillel.homework.persistence.entity.BaseModifyEntity;
import org.hillel.homework.persistence.repository.AbsRepository;
import org.hillel.homework.persistence.repository.CommonRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

public abstract class TransactionalAbsService<T extends BaseModifyEntity> {

    private final AbsRepository<T> repository;

    public TransactionalAbsService(AbsRepository<T> repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public Collection<T> findAll(QueryType queryType) {
        switch (queryType) {
            case HQL:
                return findAll();
            case NATIVE:
                return findAllAsNative();
            case NAMED:
                return findAllAsNamed();
            case CRITERIA:
                return findAllAsCriteria();
            case STORED_PROCEDURE:
                return findAllAsStoredProcedure();
            default:
                throw new IllegalArgumentException("unknown queryType");
        }
    }

    @Transactional(readOnly = true)
    public Collection<T> findAll() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public Collection<T> findAllAsNative() {
        return repository.findAllAsNative();
    }

    @Transactional(readOnly = true)
    public Collection<T> findAllAsNamed() {
        return repository.findAllAsNamed();
    }

    @Transactional(readOnly = true)
    public Collection<T> findAllAsCriteria() {
        return repository.findAllAsCriteria();
    }

    @Transactional(readOnly = true)
    public Collection<T> findAllAsStoredProcedure() {
        return repository.findAllAsStoredProcedure();
    }
}
