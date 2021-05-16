package org.hillel.homework.persistence.entity;

import lombok.*;
import org.hillel.homework.persistence.entity.enums.DirectionType;

import javax.persistence.*;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "journey")
public class JourneyEntity extends BaseModifyEntity {

    @Column(name = "station_from", length = 1000, nullable = false)
    private String stationFrom;

    @Column(name = "station_to", length = 1000, nullable = false)
    private String stationTo;

    @Column(name = "date_from", length = 1000, nullable = false)
    private Instant dateFrom;

    @Column(name = "date_to", length = 1000, nullable = false)
    private Instant dateTo;

    @Column(name = "direction", length = 20, nullable = false)
    @Enumerated(EnumType.STRING)
    private DirectionType direction = DirectionType.TO;

    @Transient
    private String route;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "journey_stop", joinColumns = @JoinColumn(name = "journey_id"))
    private Collection<StopEntity> stopPoints;

    public void addStop(@NonNull final StopEntity stop){
        if (stopPoints == null) stopPoints = new ArrayList<>();
        stopPoints.add(stop);
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vehicle_id")
    private VehicleEntity vehicle;
}
