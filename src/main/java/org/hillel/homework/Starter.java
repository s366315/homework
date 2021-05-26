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

        System.out.println("----------------");
        System.out.println("QueryType.HQL с сортировкой по id " + ticketClient.findAllJourneys(QueryType.HQL, 1, 5, "id", true));
        System.out.println("QueryType.NATIVE с сортировкой по id " + ticketClient.findAllJourneys(QueryType.NATIVE, 1, 5, "id", true));
        System.out.println("QueryType.NAMED с сортировкой по id " + ticketClient.findAllJourneys(QueryType.NAMED, 1, 5, "id", true));
        System.out.println("QueryType.CRITERIA с сортировкой по id " + ticketClient.findAllJourneys(QueryType.CRITERIA, 1, 5, "id", true));
        System.out.println("----------------");
        System.out.println("QueryType.HQL с сортировкой по name " + ticketClient.findAllVehicles(QueryType.HQL, 1, 5, "name", true));
        System.out.println("QueryType.NATIVE с сортировкой по name " + ticketClient.findAllVehicles(QueryType.NATIVE, 1, 5, "name", true));
        System.out.println("QueryType.NAMED с сортировкой по name " + ticketClient.findAllVehicles(QueryType.NAMED, 1, 5, "name", true));
        System.out.println("QueryType.CRITERIA с сортировкой по name " + ticketClient.findAllVehicles(QueryType.CRITERIA, 1, 5, "name", true));
        System.out.println("----------------");
        System.out.println("QueryType.HQL с сортировкой по active " + ticketClient.findAllStops(QueryType.HQL, 1, 5, "active", true));
        System.out.println("QueryType.NATIVE с сортировкой по active " + ticketClient.findAllStops(QueryType.NATIVE, 1, 5, "active", true));
        System.out.println("QueryType.NAMED с сортировкой по active " + ticketClient.findAllStops(QueryType.NAMED, 1, 5, "active", true));
        System.out.println("QueryType.CRITERIA с сортировкой по active " + ticketClient.findAllStops(QueryType.CRITERIA, 1, 5, "active", true));
    }
}
