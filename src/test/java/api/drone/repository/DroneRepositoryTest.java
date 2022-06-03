package api.drone.repository;

import api.drone.model.Drone;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class DroneRepositoryTest
{
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private DroneRepository droneRepository;

    @Test
    public void findByStatus()
    {
        Drone drone = new Drone( "12125", "Lightweight", 100.0, 100.0, "LOADING", "rajeev" );
        entityManager.persist( drone );
        entityManager.flush();
        //droneRepository.save(drone);
        List<Drone> actualResult = droneRepository.findByStatus( "LOADING" );
        assertThat( actualResult.size() > 0 ).isTrue();

    }


}