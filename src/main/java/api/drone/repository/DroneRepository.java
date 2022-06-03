package api.drone.repository;

import api.drone.model.Drone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface DroneRepository extends JpaRepository<Drone, String>
{

    @Query(value = "select * from drone where state =:stateCode", nativeQuery = true)
    List<Drone> findByStatus( @Param("stateCode") String state );

}
