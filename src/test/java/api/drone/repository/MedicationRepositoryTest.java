package api.drone.repository;

import api.drone.model.Drone;
import api.drone.model.Medication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class MedicationRepositoryTest
{

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private DroneRepository droneRepository;

    @Autowired
    private MedicationRepository medicationRepository;

    @Test

    public void findBySerialNumber()
    {
        Drone drone = new Drone( "12125", "Lightweight", 100.0, 100.0, "LOADING", "rajeev" );
        entityManager.persist( drone );
        entityManager.flush();
        Medication medication = new Medication("MED_00X", "Test med", 80.0, null, "Rajeev", drone  );
        entityManager.persist( medication );
        entityManager.flush();
        Optional<List<Medication>> actualResult = medicationRepository.findBySerialNumber( "12125" );
        assertThat(actualResult.isPresent() );

    }
}