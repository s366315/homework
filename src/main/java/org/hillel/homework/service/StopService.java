package org.hillel.homework.service;

import org.hillel.homework.persistence.entity.StopEntity;
import org.hillel.homework.persistence.entity.VehicleEntity;
import org.hillel.homework.persistence.repository.StopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;

@Service
public class StopService {

    private StopRepository stopRepository;

    @Autowired
    public StopService(StopRepository repository) {
        this.stopRepository = repository;
    }

    @Transactional
    public StopEntity createOrUpdate(StopEntity stopEntity){
        return stopRepository.save(stopEntity);
    }

    @Transactional
    public void remove(StopEntity stop) {
        stopRepository.delete(stop);
    }

    @Transactional
    public Collection<StopEntity> findAll() {
        return stopRepository.findAll();
    }

    @Transactional
    public Collection<StopEntity> getPageSortBy(int page, int pageSize, String orderFieldName) {
        Page<StopEntity> pageList = stopRepository.findAll(PageRequest.of(page, pageSize, Sort.by(orderFieldName)));
        return pageList.getContent();
    }
}
