package org.hillel.homework.persistence.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;

@Getter
@Setter
@MappedSuperclass
public abstract class BaseModifyEntity implements Serializable, Persistable<Integer> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "create_date")
    @CreationTimestamp
    private Instant createDate;

    @Column(name = "active")
    private boolean active = true;

    @Override
    public boolean isNew() {
        return id == null;
    }
}
