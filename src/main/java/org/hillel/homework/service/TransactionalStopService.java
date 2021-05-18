package org.hillel.homework.service;

import org.hillel.homework.persistence.entity.StopEntity;
import org.hillel.homework.persistence.repository.AbsRepository;
import org.hillel.homework.persistence.repository.StopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class TransactionalStopService extends TransactionalAbsService<StopEntity> {

    private StopRepository stopRepository;

    @Autowired
    public TransactionalStopService(StopRepository repository) {
        super(repository);
        this.stopRepository = repository;
    }

    @Transactional
    public StopEntity createOrUpdate(StopEntity stopEntity){
        return stopRepository.createOrUpdate(stopEntity);
    }

    @Transactional
    public void remove(StopEntity stop) {
        stopRepository.remove(stop);
    }
}
