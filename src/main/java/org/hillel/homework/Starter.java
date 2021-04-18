package org.hillel.homework;

import org.hillel.homework.config.RootConfig;
import org.hillel.homework.persistence.entity.CommonInfo;
import org.hillel.homework.persistence.entity.JourneyEntity;
import org.hillel.homework.persistence.entity.StopAdditionalInfoEntity;
import org.hillel.homework.persistence.entity.StopEntity;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.time.LocalDate;

public class Starter {

    public static void main(String[] args) throws SQLException {
        final ApplicationContext applicationContext = new AnnotationConfigApplicationContext(RootConfig.class);
        TicketClient ticketClient = applicationContext.getBean(TicketClient.class);

        JourneyEntity journeyEntity = new JourneyEntity();
        journeyEntity.setStationFrom("Kiev");
        journeyEntity.setArrival(LocalDate.now().plusDays(2));
        journeyEntity.setDeparture(LocalDate.now());
        journeyEntity.setStationTo("Jmerinka");

        System.out.println("journeyEntity was inserted with id: " + ticketClient.createJourney(journeyEntity));

        System.out.println(ticketClient.find("Kiev", "Jmerinka", LocalDate.now().minusDays(1), LocalDate.now()));


        StopAdditionalInfoEntity stopAdditionalInfoEntity = new StopAdditionalInfoEntity();
        stopAdditionalInfoEntity.setLatitude(10D);
        stopAdditionalInfoEntity.setLongitude(176D);
        StopEntity stopEntity = new StopEntity();
        stopEntity.addStopAdditionalInfo(stopAdditionalInfoEntity);
        CommonInfo commonInfo = new CommonInfo();
        commonInfo.setName("stop 1");
        commonInfo.setDescription("stop 1 description");
        stopEntity.setCommonInfo(commonInfo);

        ticketClient.createStop(stopEntity);
    }
}
