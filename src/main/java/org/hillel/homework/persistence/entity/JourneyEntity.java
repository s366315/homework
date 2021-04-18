package org.hillel.homework.persistence.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hillel.homework.persistence.entity.enums.DirectionType;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@ToString
@NoArgsConstructor
@Getter
@Setter
@Table(name = "journey")
public class JourneyEntity extends BaseModifyEntity<Integer> {

    @Column(name = "station_from", length = 1000, nullable = false)
    private String stationFrom;

    @Column(name = "station_to", length = 1000, nullable = false)
    private String stationTo;

    @Column(name = "date_from", length = 1000, nullable = false)
    private LocalDate departure;

    @Column(name = "date_to", length = 1000, nullable = false)
    private LocalDate arrival;

    @Column(name = "direction", length = 20, nullable = false)
    @Enumerated(EnumType.STRING)
    private DirectionType direction = DirectionType.TO;

    @Transient
    private String route;

}
