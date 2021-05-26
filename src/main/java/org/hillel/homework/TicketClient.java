package org.hillel.homework;

import lombok.NoArgsConstructor;
import org.hillel.homework.persistence.entity.JourneyEntity;
import org.hillel.homework.persistence.entity.SeatEntity;
import org.hillel.homework.persistence.entity.StopEntity;
import org.hillel.homework.persistence.entity.VehicleEntity;
import org.hillel.homework.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Optional;

@NoArgsConstructor
@Component
public class TicketClient {

    @Autowired
    private TransactionalJourneyService journeyService;

    @Autowired
    private TransactionalStopService stopService;

    @Autowired
    private TransactionalSeatService seatInfoService;

    @Autowired
    private TransactionalVehicleService vehicleService;

    @Autowired
    private Environment environment;

    public JourneyEntity createOrUpdateJourney(JourneyEntity journey) {
        return journeyService.createOrUpdate(journey);
    }

    public Optional<JourneyEntity> findJourneyById(Integer id, boolean withDependencies){
        return id == null ? Optional.empty() : journeyService.findById(id, withDependencies);
    }

    public StopEntity createOrUpdateStop(StopEntity stopEntity){
        return stopService.createOrUpdate(stopEntity);
    }

    public SeatEntity createOrUpdateSeat(SeatEntity seatInfo) {
        return seatInfoService.createOrUpdate(seatInfo);
    }

    public VehicleEntity createOrUpdateVehicle(VehicleEntity vehicle) {
        return vehicleService.createOrUpdate(vehicle);
    }

    public void removeJourney(JourneyEntity journey) {
        journeyService.remove(journey);
    }

    public void removeJourneyById(Integer journeyId) {
        journeyService.removeById(journeyId);
    }

    public void removeVehicle(VehicleEntity vehicleEntity){
        vehicleService.remove(vehicleEntity);
    }

    public void removeVehicleById(Integer id){
        vehicleService.removeById(id);
    }

    public void removeSeatInfo(SeatEntity seatInfo){
        seatInfoService.remove(seatInfo);
    }

    public void removeSeatInfoById(Integer id){
        seatInfoService.removeById(id);
    }

    public void removeStop(StopEntity stop) {
        stopService.remove(stop);
    }

    public Collection<JourneyEntity> findAllJourneys(QueryType queryType, int pageNumber, int pageSize, String orderFieldName, boolean orderAsc){
        QueryRequest request = new QueryRequest(queryType, pageNumber, pageSize, orderFieldName, orderAsc);
        return journeyService.findAll(request);
    }
    public Collection<SeatEntity> findAllSeatsInfo(QueryType queryType, int pageNumber, int pageSize, String orderFieldName, boolean orderAsc){
        QueryRequest request = new QueryRequest(queryType, pageNumber, pageSize, orderFieldName, orderAsc);
        return seatInfoService.findAll(request);
    }
    public Collection<StopEntity> findAllStops(QueryType queryType, int pageNumber, int pageSize, String orderFieldName, boolean orderAsc){
        QueryRequest request = new QueryRequest(queryType, pageNumber, pageSize, orderFieldName, orderAsc);
        return stopService.findAll(request);
    }
    public Collection<VehicleEntity> findAllVehicles(QueryType queryType, int pageNumber, int pageSize, String orderFieldName, boolean orderAsc){
        QueryRequest request = new QueryRequest(queryType, pageNumber, pageSize, orderFieldName, orderAsc);
        return vehicleService.findAll(request);
    }
}
