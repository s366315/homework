package org.hillel.homework.persistence.repository;

import org.hillel.homework.persistence.entity.StopEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface StopRepository extends CommonRepository<StopEntity>, JpaSpecificationExecutor<StopEntity> {

}
