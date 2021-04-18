package org.hillel.homework.persistence.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "stop")
@NoArgsConstructor
@ToString
@Getter
@Setter
public class StopEntity extends BaseModifyEntity<Integer> {

    @Embedded
    private CommonInfo commonInfo;

    @OneToOne(mappedBy = "stop", cascade = {CascadeType.PERSIST})
    private StopAdditionalInfoEntity additionalInfo;

    public void addStopAdditionalInfo(final StopAdditionalInfoEntity stopAdditionalInfo) {
        if (stopAdditionalInfo == null) {
            additionalInfo = null;
            return;
        }

        stopAdditionalInfo.setStop(this);
        additionalInfo = stopAdditionalInfo;
    }
}
