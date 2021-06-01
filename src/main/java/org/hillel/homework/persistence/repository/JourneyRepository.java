package org.hillel.homework.persistence.repository;

import org.hillel.homework.persistence.entity.JourneyEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface JourneyRepository  extends CommonRepository<JourneyEntity>, JpaSpecificationExecutor<JourneyEntity> {

}
