package api.drone.validate;


import api.drone.dto.DroneDTO;
import api.drone.dto.MedicationDTO;
import api.drone.dto.Model;
import api.drone.dto.State;
import api.drone.dto.ValidateResponse;
import api.drone.model.Drone;
import api.drone.model.Medication;
import api.drone.utility.StringUtility;

import java.util.regex.Pattern;

public class DroneValidator
{
    public static ValidateResponse validateDrone( DroneDTO drone )
    {

        if( StringUtility.isNullOrEmpty( drone.getSerialNumber() ) )
        {
            return new ValidateResponse( false, "Mandatory parameter serial number missing" );
        }
        if( drone.getSerialNumber().trim().length() > 100 )
        {
            return new ValidateResponse( false, "Serial number max character limit(100) exceeds." );
        }
        if( StringUtility.isNullOrEmpty( drone.getModel() ) )
        {
            return new ValidateResponse( false, "Mandatory parameter Model missing" );
        }
        if( Model.valueOf( drone.getModel() ) == null )
        {
            return new ValidateResponse( false, "Invalid Model" );
        }
        if( StringUtility.isNullOrEmpty( drone.getState() ) )
        {
            return new ValidateResponse( false, "Mandatory parameter State missing" );
        }
        if( State.valueOf( drone.getState() ) == null )
        {
            return new ValidateResponse( false, "Invalid State" );
        }
        if( drone.getWeightLimit() > 500 )
        {
            return new ValidateResponse( false, "Weight limit max value(500) exceeds." );
        }
        //        if( drone.getBatteryCapacity() == null )
        //        {
        //            return new ValidateResponse( false, "Mandatory parameter Battery capacity missing" );
        //        }
        if( drone.getUsername() == null )
        {
            return new ValidateResponse( false, "Mandatory parameter username missing." );
        }

        return new ValidateResponse();
    }

    public static ValidateResponse validateState( String state )
    {
        if( !( state == null || State.valueOf( state ) != null ) )
        {
            return new ValidateResponse( false, "Invalid State" );
        }
        return new ValidateResponse();
    }

    public static ValidateResponse validateMedication( MedicationDTO medication )
    {
        if( StringUtility.isNullOrEmpty( medication.getCode() ) )
        {
            return new ValidateResponse( false, "Mandatory parameter code missing" );
        }
        if( validateCode( medication.getCode() ) )
        {
            return new ValidateResponse( false, "Value for code is not in the correct format." );
        }
        if( StringUtility.isNullOrEmpty( medication.getName() ) )
        {
            return new ValidateResponse( false, "Mandatory parameter name missing" );
        }
        if( validateName( medication.getName() ) )
        {
            return new ValidateResponse( false, "Value for name is not in the correct format." );
        }
        if( StringUtility.isNullOrEmpty( medication.getUsername() ) )
        {
            return new ValidateResponse( false, "Mandatory parameter username missing." );
        }
        return new ValidateResponse();
    }

    private static boolean validateName( String name )
    {
        return Pattern.matches( "[a-zA-Z0-9_\\-]", name );
    }

    private static boolean validateCode( String code )
    {
        return Pattern.matches( "[A-Z0-9_]", code );
    }

}
