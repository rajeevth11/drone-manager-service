package api.drone.mapper;

import api.drone.dto.DroneDTO;
import api.drone.dto.MedicationDTO;
import api.drone.model.Drone;
import api.drone.model.Medication;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CriteriaMapper
{
    CriteriaMapper INSTANCE = Mappers.getMapper( CriteriaMapper.class );

    Drone mapDrone( DroneDTO droneDTO );

    Medication mapMedication( MedicationDTO medicationDTO );


}
