package org.hillel.homework.service;

import org.hillel.homework.persistence.entity.SeatEntity;
import org.hillel.homework.persistence.entity.VehicleEntity;
import org.hillel.homework.persistence.repository.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;

@Service
public class SeatService {
    private SeatRepository seatRepository;

    @Autowired
    public SeatService(SeatRepository repository) {
        this.seatRepository = repository;
    }

    @Transactional
    public SeatEntity createOrUpdate(SeatEntity seatEntity){
        return seatRepository.save(seatEntity);
    }

    @Transactional
    public void remove(SeatEntity seatInfo){
        seatRepository.delete(seatInfo);
    }

    @Transactional
    public void removeById(Integer id){
        seatRepository.deleteById(id);
    }

    @Transactional
    public Collection<SeatEntity> findAll() {
        return seatRepository.findAll();
    }

    @Transactional
    public Collection<SeatEntity> getPageSortBy(int page, int pageSize, String orderFieldName) {
        Page<SeatEntity> pageList = seatRepository.findAll(PageRequest.of(page, pageSize, Sort.by(orderFieldName)));
        return pageList.getContent();
    }
}
