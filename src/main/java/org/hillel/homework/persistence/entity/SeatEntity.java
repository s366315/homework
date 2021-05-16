package org.hillel.homework.persistence.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "seat")
@NoArgsConstructor
@Getter
@Setter
public class SeatEntity extends BaseModifyEntity {
    @ManyToOne(cascade =  {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "vehicle_id", nullable = false)
    private VehicleEntity vehicle;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "journey_id", nullable = false)
    private JourneyEntity journey;

    @Column(name = "free_seats")
    private Integer freeSeats;
}
