import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@ComponentScan (basePackages = { "api.drone"})
@EnableJpaRepositories(basePackages = {"api.drone.repository"})
@EntityScan( basePackages = {"api.drone.model"})

public class DroneApplication
{
    final static Logger logger = Logger.getLogger( DroneApplication.class );
    public static void main(String[] args)
    {
        logger.log( Level.ERROR, "<<<<<<<<<<<<<<<<<<<<Starting Application>>>>>>>>>>>>>>>>>>>" );
        SpringApplication.run(DroneApplication.class, args);
    }
}
