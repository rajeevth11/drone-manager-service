package api.drone.service;

import api.drone.dto.ResponseWrapper;
import api.drone.model.Drone;
import api.drone.model.Medication;
import org.springframework.http.ResponseEntity;

import java.util.List;


public interface DroneMangeService
{
    ResponseEntity<ResponseWrapper<Drone>> registerDrone( Drone drone );

    ResponseEntity<ResponseWrapper<List<Drone>>> loadAll( String state );

    ResponseEntity<ResponseWrapper<Drone>> findDroneById( String serialNumber );

    ResponseEntity<ResponseWrapper<Medication>> loadMedication( String serialNumber, Medication medication );

    ResponseEntity<ResponseWrapper<List<Medication>>> getMedicationByDrone( String serialNumber );
}
