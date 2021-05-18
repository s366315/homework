package org.hillel.homework;

import org.hillel.homework.config.RootConfig;
import org.hillel.homework.persistence.entity.*;
import org.hillel.homework.service.QueryType;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.time.Instant;

public class Starter {

    public static void main(String[] args) {
        final ApplicationContext applicationContext = new AnnotationConfigApplicationContext(RootConfig.class);
        TicketClient ticketClient = applicationContext.getBean(TicketClient.class);

        VehicleEntity vehicleEntity = new VehicleEntity();
        vehicleEntity.setName("MAN");
        vehicleEntity = ticketClient.createOrUpdateVehicle(vehicleEntity);

        JourneyEntity journeyEntity = new JourneyEntity();
        journeyEntity.setStationFrom("Odessa");
        journeyEntity.setDateTo(Instant.now());
        journeyEntity.setDateFrom(Instant.now().minusSeconds(1000));
        journeyEntity.setStationTo("St.Petersburg");

        StopAdditionalInfoEntity stopAdditionalInfoEntity = new StopAdditionalInfoEntity();
        stopAdditionalInfoEntity.setLatitude(10D);
        stopAdditionalInfoEntity.setLongitude(176D);

        StopEntity stopEntity = new StopEntity();
        stopEntity.addStopAdditionalInfo(stopAdditionalInfoEntity);
        CommonInfo commonInfo = new CommonInfo();
        commonInfo.setName("stop 101");
        commonInfo.setDescription("stop 101 description");
        stopEntity.setCommonInfo(commonInfo);

        journeyEntity.addStop(stopEntity);

        journeyEntity.setVehicle(vehicleEntity);
        journeyEntity = ticketClient.createOrUpdateJourney(journeyEntity);


        SeatEntity seatEntity = new SeatEntity();
        seatEntity.setVehicle(vehicleEntity);
        seatEntity.setFreeSeats(2);
        seatEntity.setJourney(journeyEntity);

        vehicleEntity.addSeatInfo(seatEntity);

        vehicleEntity = ticketClient.createOrUpdateVehicle(vehicleEntity);


        System.out.println("QueryType.HQL" + ticketClient.findAllJourneys(QueryType.HQL));
        System.out.println("QueryType.NATIVE" + ticketClient.findAllJourneys(QueryType.NATIVE));
        System.out.println("QueryType.NAMED" + ticketClient.findAllJourneys(QueryType.NAMED));
        System.out.println("QueryType.CRITERIA" + ticketClient.findAllJourneys(QueryType.CRITERIA));
        System.out.println("QueryType.STORED_PROCEDURE" + ticketClient.findAllJourneys(QueryType.STORED_PROCEDURE));
    }
}
