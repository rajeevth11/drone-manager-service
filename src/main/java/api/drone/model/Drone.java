package api.drone.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@ToString
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table (name = "drone")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Drone {
    @Column (name = "serial_number")
    @Id
    String serialNumber;
    String model;
    @Column (name = "weight_limit")
    double weightLimit;
    @Column (name = "battery_capacity")
    double batteryCapacity;
    String state;
    @Column (name = "modified_user")
    String username;
    @Column (name = "last_modified")
    private Timestamp lastModified;
    @OneToMany(mappedBy = "drone", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    Set<Medication> medications;


    public Drone( String serialNumber, String model, double weightLimit, double batteryCapacity, String state, String username )
    {
        this.serialNumber = serialNumber;
        this.model = model;
        this.weightLimit = weightLimit;
        this.batteryCapacity = batteryCapacity;
        this.state = state;
        this.username = username;
    }
}
