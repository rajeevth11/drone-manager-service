package api.drone.utility;

public class StringUtility {

    public static boolean isNullOrEmpty( String input)
    {
        return input == null || input.trim().isEmpty() ;
    }
}
