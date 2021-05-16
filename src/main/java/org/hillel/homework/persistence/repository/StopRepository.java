package org.hillel.homework.persistence.repository;

import org.hillel.homework.persistence.entity.StopEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@Repository
public class StopRepository  extends AbsRepository<StopEntity> {


}
