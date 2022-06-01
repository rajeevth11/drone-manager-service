package api.drone.scheduler;

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
public class BatteryMonitoringJob
{
    final static Logger logger = Logger.getLogger( BatteryMonitoringJob.class );

    @Autowired
    private DroneRepository droneRepository;

    //@Async
    @Scheduled(fixedRate = 10000 )
    public void batteryMonitor()
    {
        SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss.SSS" );
        String strDate = sdf.format( new Date() );
        List<Drone> droneList = droneRepository.findAll();
        for( Drone drone : droneList )
        {
            logger.info( strDate + "Battery Level for Drone >>>> SerialNumber : " + drone.getSerialNumber() + " Battery Capacity " + drone.getBatteryCapacity() );
        }
    }
}