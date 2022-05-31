package api.drone.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DroneDTO {
    private String serialNumber;
    private String model;
    private double weightLimit;
    private double batteryCapacity;
    private String state;
    private String username;

}
