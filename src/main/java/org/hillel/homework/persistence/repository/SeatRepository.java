package org.hillel.homework.persistence.repository;

import org.hillel.homework.persistence.entity.SeatEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface SeatRepository extends CommonRepository<SeatEntity>, JpaSpecificationExecutor<SeatEntity> {

}
