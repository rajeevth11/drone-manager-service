package api.drone.controller;

import api.drone.dto.MedicationDTO;
import api.drone.dto.ResponseWrapper;
import api.drone.dto.ValidateResponse;
import api.drone.mapper.CriteriaMapper;
import api.drone.dto.DroneDTO;
import api.drone.model.Drone;
import api.drone.model.Medication;
import api.drone.service.DroneMangeService;
import api.drone.validate.DroneValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "drone-manager-service/api")
public class DroneController
{
    @Autowired
    private DroneMangeService droneMangeService;

    @RequestMapping(method = {RequestMethod.POST}, value = "/drones", produces = "application/json")
    public ResponseEntity<ResponseWrapper<Drone>> registerDrone( @RequestHeader("username") String username, @RequestBody(required = true) DroneDTO drone )
    {
        drone.setUsername( username );
        ValidateResponse validateResponse = DroneValidator.validateDrone( drone );
        if( validateResponse.isValid() )
        {
            return droneMangeService.registerDrone( CriteriaMapper.INSTANCE.mapDrone( drone ) );
        }
        else
        {
            return new ResponseEntity( new ResponseWrapper<Drone>( ResponseWrapper.ERROR, validateResponse.getErrorMessage() ), HttpStatus.BAD_REQUEST );
            //return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Error Message");
        }
    }

    @RequestMapping(method = {RequestMethod.POST}, value = "/drones{serialNumber}/medication", produces = "application/json")
    public ResponseEntity<ResponseWrapper<Medication>> loadMedication( @RequestHeader("username") String username, @PathVariable String serialNumber, @RequestBody(required = true) MedicationDTO medication )
    {
        medication.setUsername( username );
        if( DroneValidator.validateMedication( medication ).isValid() )
        {
            return droneMangeService.loadMedication( serialNumber, CriteriaMapper.INSTANCE.mapMedication( medication ) );
        }
        else
        {
            return new ResponseEntity( HttpStatus.BAD_REQUEST );
            //return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Error Message");
        }
    }

    @RequestMapping(method = {RequestMethod.GET}, value = "/drones{serialNumber}/medication", produces = "application/json")
    public ResponseEntity<ResponseWrapper<List<Medication>>> getMedicationByDrone( @PathVariable String serialNumber )
    {
        return droneMangeService.getMedicationByDrone( serialNumber );
    }

    /**
     * Get list of Drones matching the criteria.
     *
     * @param state - String (Optional parameter )
     * @return ResponseEntity
     */
    @RequestMapping(method = {RequestMethod.GET}, value = "/drones", produces = "application/json")
    public ResponseEntity<ResponseWrapper<List<Drone>>> filterDrones( @RequestParam(required = false) String state )
    {
        if( !DroneValidator.validateState( state ).isValid() )
        {
            return new ResponseEntity( HttpStatus.BAD_REQUEST );
        }
        else
        {
            return droneMangeService.loadAll( state );
        }
    }

    /**
     * Find the drone by id
     *
     * @param serialNumber of type String
     * @return Drone object
     */
    @RequestMapping(method = {RequestMethod.GET}, value = "/drones{serialNumber}", produces = "application/json")
    public ResponseEntity<ResponseWrapper<Drone>> retrieveDrone( @PathVariable String serialNumber )
    {
        return droneMangeService.findDroneById(serialNumber);
    }
}
