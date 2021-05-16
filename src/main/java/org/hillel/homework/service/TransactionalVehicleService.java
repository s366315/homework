package org.hillel.homework.service;

import org.hillel.homework.persistence.entity.VehicleEntity;
import org.hillel.homework.persistence.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class TransactionalVehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Transactional
    public VehicleEntity createOrUpdate(VehicleEntity vehicleEntity){
        return vehicleRepository.createOrUpdate(vehicleEntity);
    }
}
