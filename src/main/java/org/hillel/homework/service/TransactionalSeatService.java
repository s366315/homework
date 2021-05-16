package org.hillel.homework.service;

import org.hillel.homework.persistence.entity.SeatEntity;
import org.hillel.homework.persistence.repository.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class TransactionalSeatService {

    @Autowired
    private SeatRepository seatRepository;

    @Transactional
    public SeatEntity createOrUpdate(SeatEntity seatEntity){
        return seatRepository.createOrUpdate(seatEntity);
    }
}
