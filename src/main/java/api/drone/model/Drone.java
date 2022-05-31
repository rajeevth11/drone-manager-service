package api.drone.model;

import lombok.Getter;
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

@Getter
@Setter
@ToString
@Entity
@Table (name = "drone")
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
    List<Medication> medications;


}
