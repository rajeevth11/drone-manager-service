package api.drone.repository;

import api.drone.model.Medication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface MedicationRepository extends JpaRepository<Medication, String>
{
    @Query(value = "select * from medication where serial_number =:serialNum", nativeQuery = true)
    Optional<List<Medication>> findBySerialNumber( @Param("serialNum") String serialNumber );


}
