package org.hillel.homework.persistence.repository;

import org.springframework.data.domain.Persistable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;

import java.util.List;

@NoRepositoryBean
public interface CommonRepository<T extends Persistable<Integer>> extends JpaRepository<T, Integer> {



    @Query("select e from #{#entityName} e where e.active = true")
    List<T> findOnlyActive();

    @Modifying
    @Query("update #{#entityName} e set e.active = false where e.id = :id")
    void disableById(@Param("id") Integer id);
}
