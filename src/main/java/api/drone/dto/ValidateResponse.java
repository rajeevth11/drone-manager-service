package api.drone.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ValidateResponse
{
    boolean isValid;
    String errorMessage;

    public ValidateResponse ()
    {
        isValid = true;
        errorMessage = "";
    }

    public ValidateResponse (boolean isValid, String errorMessage)
    {
        this.isValid = isValid;
        this.errorMessage = errorMessage;
    }

}
