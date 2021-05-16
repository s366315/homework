package org.hillel.homework.persistence.repository;

import org.hillel.homework.persistence.entity.VehicleEntity;
import org.springframework.stereotype.Repository;

@Repository
public class VehicleRepository extends AbsRepository<VehicleEntity> {
    public VehicleRepository() {
        super(VehicleEntity.class);
    }
}
