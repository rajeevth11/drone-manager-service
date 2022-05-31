package api.drone.repository;

import api.drone.model.Drone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

@Component
public interface DroneRepository extends JpaRepository<Drone, String>
{

    @Query(value = "select * from drone where sate =:stateCode", nativeQuery = true)
    void findByStatus( @Param("stateCode") String state );

}
