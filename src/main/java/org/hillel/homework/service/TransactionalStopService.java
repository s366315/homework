package org.hillel.homework.service;

import org.hillel.homework.persistence.entity.StopEntity;
import org.hillel.homework.persistence.repository.StopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class TransactionalStopService {

    @Autowired
    private StopRepository stopRepository;

    @Transactional
    public int createStop(final StopEntity entity) {
        return stopRepository.create(entity);
    }
}
