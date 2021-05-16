package org.hillel.homework.persistence.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name = "stop")
@NoArgsConstructor
@Getter
@Setter
public class StopEntity extends BaseModifyEntity {

    @Embedded
    private CommonInfo commonInfo;

    @OneToOne(mappedBy = "stop", cascade = {CascadeType.PERSIST})
    private StopAdditionalInfoEntity additionalInfo;

    @ManyToMany(mappedBy = "stopPoints")
    private Collection<JourneyEntity> journeys;

    public void addStopAdditionalInfo(final StopAdditionalInfoEntity stopAdditionalInfo) {
        if (stopAdditionalInfo == null) {
            additionalInfo = null;
            return;
        }

        stopAdditionalInfo.setStop(this);
        additionalInfo = stopAdditionalInfo;
    }

    private void addJourney(@NonNull JourneyEntity journey) {
        if (journeys == null) {
            journeys = new ArrayList<>();
        }
        journeys.add(journey);
    }
}
