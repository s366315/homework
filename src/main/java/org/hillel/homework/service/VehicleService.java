package org.hillel.homework.service;

import org.hillel.homework.persistence.entity.VehicleEntity;
import org.hillel.homework.persistence.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;

@Service
public class VehicleService {

    private VehicleRepository vehicleRepository;

    @Autowired
    public VehicleService(VehicleRepository repository) {
        this.vehicleRepository = repository;
    }

    @Transactional
    public Collection<VehicleEntity> findAll() {
        return vehicleRepository.findAll();
    }

    @Transactional
    public Collection<VehicleEntity> getPageByName(int page, int pageSize, String name) {
        Page<VehicleEntity> pageList = vehicleRepository.findAll(VehicleRepository.byName(name), PageRequest.of(page, pageSize));
        return pageList.getContent();
    }

    @Transactional
    public Collection<VehicleEntity> getPageSortBy(int page, int pageSize, String orderFieldName) {
        Page<VehicleEntity> pageList = vehicleRepository.findAll(PageRequest.of(page, pageSize, Sort.by(orderFieldName)));
        return pageList.getContent();
    }

    @Transactional
    public VehicleEntity createOrUpdate(VehicleEntity vehicleEntity) {
        return vehicleRepository.save(vehicleEntity);
    }

    @Transactional
    public void remove(VehicleEntity vehicle) {
        vehicleRepository.delete(vehicle);
    }

    @Transactional
    public void removeById(Integer id) {
        vehicleRepository.deleteById(id);
    }
}
