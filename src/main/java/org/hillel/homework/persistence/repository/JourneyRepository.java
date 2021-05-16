package org.hillel.homework.persistence.repository;

import org.hillel.homework.persistence.entity.JourneyEntity;
import org.hillel.homework.persistence.entity.VehicleEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class JourneyRepository  extends AbsRepository<JourneyEntity> {

    @Override
    public JourneyEntity createOrUpdate(JourneyEntity entity) {
        final VehicleEntity vehicle = entity.getVehicle();
        if (vehicle != null){
            if (!entityManager.contains(vehicle)){
                entity.setVehicle(entityManager.merge(vehicle));
            }
        }

        return super.createOrUpdate(entity);
    }
}
