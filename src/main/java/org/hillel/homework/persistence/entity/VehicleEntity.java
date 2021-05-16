package org.hillel.homework.persistence.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "vehicle")
@NoArgsConstructor
@Getter
@Setter
public class VehicleEntity extends BaseModifyEntity{
    @Column(name = "name" )
    private String name;

    @OneToMany(mappedBy = "vehicle")
    private Set<JourneyEntity> journeys;

    @OneToMany(mappedBy = "vehicle", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<SeatEntity> seats;

    public void addJourney(@NonNull final JourneyEntity journey){
        if (journeys == null){
            journeys = new HashSet<>();
        }
        journeys.add(journey);
        journey.setVehicle(this);
    }

    public void addSeatInfo(@NonNull SeatEntity seatInfo){
        if (seats == null) seats = new ArrayList<>();
        seats.add(seatInfo);
        seatInfo.setVehicle(this);
    }
}
