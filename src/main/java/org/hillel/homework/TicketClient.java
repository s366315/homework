package org.hillel.homework;

import org.hillel.homework.persistence.entity.JourneyEntity;
import org.hillel.homework.persistence.entity.StopEntity;
import org.hillel.homework.service.JourneyService;
import org.hillel.homework.service.TransactionalStopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Collection;

@Component
public class TicketClient {
    @Autowired
    private JourneyService transactionalJourneyService;

    @Autowired
    private TransactionalStopService stopService;

    public int createJourney(final JourneyEntity journeyEntity) {
        return transactionalJourneyService.createJourney(journeyEntity);
    }

    public int createStop(final StopEntity stopEntity) {
        return stopService.createStop(stopEntity);
    }

    public Collection<Journey> find(String stationFrom, String stationTo, LocalDate departure, LocalDate arrival) throws SQLException {
        return transactionalJourneyService.find(stationFrom, stationTo, departure, arrival);
    }
}
