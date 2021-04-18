package org.hillel.homework.service;

import org.hillel.homework.persistence.entity.JourneyEntity;
import org.hillel.homework.persistence.repository.JourneyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Service(value = "transactionalJourneyService")
public class TransactionalJourneyService implements JourneyService<JourneyEntity> {

    @Autowired
    private JourneyRepository<JourneyEntity> journeyRepository;

    @Override
    public Collection find(String stationFrom, String stationTo, LocalDate dateFrom, LocalDate dateTo) {
        List<JourneyEntity> out = new ArrayList<>();
        for (JourneyEntity item : journeyRepository.getJourneys()) {
            if (item.getDeparture().equals(dateFrom) &&
                    item.getArrival().equals(dateTo) &&
                    item.getStationFrom().equals(stationFrom) &&
                    item.getStationTo().equals(stationTo)) {
                out.add(item);
            }
        }
        return Collections.unmodifiableList(out);
    }


    @Override
    public int createJourney(final JourneyEntity entity) {
        return journeyRepository.create(entity);
    }
}
