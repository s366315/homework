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
        vehicleEntity.setName("BAmbassador");
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
//        System.out.println("с сортировкой по id " + ticketClient.findAllJourneys( 0, 5, VehicleEntity_.ID));
        System.out.println("----------------");
        System.out.println("с сортировкой по name " + ticketClient.findAllVehicles( 0, 50, VehicleEntity_.NAME));
        System.out.println("поиск по имени " + ticketClient.findAllVehiclesByName( 0, 50, "MAN"));
        System.out.println("----------------");
//        System.out.println("с сортировкой по active " + ticketClient.findAllStops( 0, 5, VehicleEntity_.ACTIVE));
    }
}
