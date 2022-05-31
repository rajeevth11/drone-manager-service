package scheduler;

import api.drone.model.Drone;
import api.drone.repository.DroneRepository;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Component
//@EnableScheduling
//@EnableAsync
public class BatteryMonitoringJob
{
    final static Logger logger = Logger.getLogger( BatteryMonitoringJob.class );

    @Autowired
    private DroneRepository droneRepository;

    @Scheduled(fixedRate = 100)
    public void scheduleFixedRateTaskAsync() throws InterruptedException
    {
        List<Drone> droneList = droneRepository.findAll();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        Date now = new Date();
        String strDate = sdf.format(now);
        DecimalFormat decFormatter = new DecimalFormat( "#%" );
        for( Drone drone : droneList )
        {
            System.out.println("hiii");
            logger.info( strDate + " >>>Drone SerialNumber : " + drone.getSerialNumber() + " Battery Capacity " + decFormatter.format( drone.getBatteryCapacity() ) );
        }
    }
}