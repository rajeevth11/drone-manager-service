package api.drone.service;

import api.drone.dto.ResponseWrapper;
import api.drone.dto.State;
import api.drone.model.Drone;
import api.drone.model.Medication;
import api.drone.repository.DroneRepository;
import api.drone.repository.MedicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DroneMangeServiceImpl implements DroneMangeService
{
    @Autowired
    private DroneRepository droneRepository;

    @Autowired
    private MedicationRepository medicationRepository;

    /**
     * Register a drone.
     *
     * @param drone
     * @return
     */
    @Override
    public ResponseEntity<ResponseWrapper<Drone>> registerDrone( Drone drone )
    {
        ResponseEntity<ResponseWrapper<Drone>> droneResponseEntity = null;
        try
        {
            if( ( droneRepository.findById( drone.getSerialNumber() ).isPresent() ) )
            {
                droneResponseEntity = new ResponseEntity( new ResponseWrapper<Drone>( ResponseWrapper.ERROR, "Drone already available with the the serial number." ), HttpStatus.BAD_REQUEST );
            }
            else
            {
                droneRepository.save( drone );
                Drone returnDrone = droneRepository.findById( drone.getSerialNumber() ).get();
                droneResponseEntity = new ResponseEntity( new ResponseWrapper<Drone>( ResponseWrapper.SUCCESS, "Drone created successfully.", returnDrone ), HttpStatus.CREATED );
            }
        }
        catch( Exception e )
        {
            droneResponseEntity = new ResponseEntity( new ResponseWrapper<Drone>( ResponseWrapper.ERROR, "Error while processing the request : " + e.getMessage() ), HttpStatus.INTERNAL_SERVER_ERROR );
        }
        return droneResponseEntity;

    }


    /**
     * Get list of Drones matching the criteria.
     *
     * @param state - String (Optional parameter )
     * @return ResponseEntity
     */
    @Override
    public ResponseEntity<ResponseWrapper<List<Drone>>> loadAll( String state )
    {
        List<Drone> droneList = null;
        if( state != null )
        {
            droneRepository.findByStatus( state );
        }
        else
        {
            droneList = droneRepository.findAll();
        }

        return droneList != null && droneList.size() > 0 ?
        new ResponseEntity<ResponseWrapper<List<Drone>>>( new ResponseWrapper( ResponseWrapper.SUCCESS, "Drone loaded successfully.", droneList ), HttpStatus.OK ):
        new ResponseEntity<ResponseWrapper<List<Drone>>>( new ResponseWrapper( ResponseWrapper.ERROR, "No results found." ), HttpStatus.NOT_FOUND );

    }

    /**
     * Find the drone by id
     *
     * @param serialNumber of type String
     * @return Drone object
     */
    @Override public ResponseEntity<ResponseWrapper<Drone>> findDroneById( String serialNumber )
    {
        Optional<Drone> returnDrone = droneRepository.findById( serialNumber );
        return returnDrone.isPresent() ? new ResponseEntity<ResponseWrapper<Drone>>( new ResponseWrapper( ResponseWrapper.SUCCESS, "Results loaded successfully.", returnDrone.get()  ), HttpStatus.OK ):
                new ResponseEntity<ResponseWrapper<Drone>>( new ResponseWrapper( ResponseWrapper.ERROR, "No results found." ), HttpStatus.NOT_FOUND );

    }


    @Override public ResponseEntity<ResponseWrapper<Medication>> loadMedication( String serialNumber, Medication medication )
    {
        ResponseEntity<ResponseWrapper<Medication>> medicationResponseEntity;
        try
        {
            Optional<Drone> returnDrone = droneRepository.findById( serialNumber );
            if( returnDrone.isPresent() )
            {
                Drone droneObject = returnDrone.get();
                if( ( medicationRepository.findById( medication.getCode() ).isPresent() ) )
                {
                    medicationResponseEntity = new ResponseEntity<ResponseWrapper<Medication>>( new ResponseWrapper( ResponseWrapper.ERROR, "Medication found with the same code."), HttpStatus.BAD_REQUEST );
                }
                else if( droneObject.getWeightLimit() > medication.getWeight() )
                {
                    medicationResponseEntity = new ResponseEntity<ResponseWrapper<Medication>>( new ResponseWrapper( ResponseWrapper.ERROR, "Medication weight exceeds."), HttpStatus.BAD_REQUEST );
                }
                else if( droneObject.getBatteryCapacity() < 0.25 )
                {
                    medicationResponseEntity = new ResponseEntity<ResponseWrapper<Medication>>( new ResponseWrapper( ResponseWrapper.ERROR, "Battery capacity not enough."), HttpStatus.BAD_REQUEST );
                }
                else
                {
                    droneObject.getMedications().add( medication );
                    droneObject.setState( State.LOADING.toString() );
                    droneRepository.save( droneObject );
                    //medication.setDrone( droneObject );
                    // medicationRepository.save( medication );
                    Medication returnMedication = medicationRepository.findById( medication.getCode() ).get();
                    medicationResponseEntity = new ResponseEntity<ResponseWrapper<Medication>>( new ResponseWrapper( ResponseWrapper.SUCCESS, "Successfully created.", returnMedication), HttpStatus.CREATED );
                }
            }
            else
            {
                //invalid serial no
                medicationResponseEntity = new ResponseEntity<ResponseWrapper<Medication>>( new ResponseWrapper( ResponseWrapper.ERROR, "Invalid serial number."), HttpStatus.INTERNAL_SERVER_ERROR );

            }
        }
        catch( Exception e )
        {
            medicationResponseEntity = new ResponseEntity<ResponseWrapper<Medication>>( new ResponseWrapper( ResponseWrapper.ERROR, "Error while processing ."+ e.getMessage() ), HttpStatus.INTERNAL_SERVER_ERROR );
        }
        return medicationResponseEntity;
    }

    @Override public ResponseEntity<ResponseWrapper<List<Medication>>> getMedicationByDrone( String serialNumber )
    {
        Optional<List<Medication>> returnMedications = medicationRepository.findBySerialNumber( serialNumber );
        return returnMedications.isPresent() ? new ResponseEntity<ResponseWrapper<List<Medication>>>( new ResponseWrapper( ResponseWrapper.SUCCESS, "Results loaded successfully.", returnMedications.get()  ), HttpStatus.OK ):
                new ResponseEntity<ResponseWrapper<List<Medication>>>( new ResponseWrapper( ResponseWrapper.ERROR, "No results found." ), HttpStatus.NOT_FOUND );

    }

}
