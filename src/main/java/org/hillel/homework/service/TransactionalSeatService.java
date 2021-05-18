package org.hillel.homework.service;

import org.hillel.homework.persistence.entity.SeatEntity;
import org.hillel.homework.persistence.repository.AbsRepository;
import org.hillel.homework.persistence.repository.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class TransactionalSeatService extends TransactionalAbsService<SeatEntity> {
    private SeatRepository seatRepository;

    @Autowired
    public TransactionalSeatService(SeatRepository repository) {
        super(repository);
        this.seatRepository = repository;
    }

    @Transactional
    public SeatEntity createOrUpdate(SeatEntity seatEntity){
        return seatRepository.createOrUpdate(seatEntity);
    }

    @Transactional
    public void remove(SeatEntity seatInfo){
        seatRepository.remove(seatInfo);
    }

    @Transactional
    public void removeById(Integer id){
        seatRepository.removeById(id);
    }
}
