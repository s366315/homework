package org.hillel.homework.persistence.entity;

import lombok.*;

import javax.persistence.*;
import java.util.*;

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
    private List<SeatEntity> seatInfos;

    public void addJourney(@NonNull final JourneyEntity journey){
        if (journeys == null){
            journeys = new HashSet<>();
        }
        journeys.add(journey);
        journey.setVehicle(this);
    }

    public void addSeatInfo(@NonNull SeatEntity seatInfo){
        if (seatInfos == null) seatInfos = new ArrayList<>();
        seatInfos.add(seatInfo);
        seatInfo.setVehicle(this);
    }
}
