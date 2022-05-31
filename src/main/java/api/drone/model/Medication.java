package api.drone.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.sql.Timestamp;

@Getter
@Setter
@ToString
@Entity
@Table(name = "medication")
public class Medication {
    @Id
    String code;
    String name;
    double weight;
    String image;
    @Column(name = "modified_user")
    String username;
    @Column (name = "last_modified")
    Timestamp lastModified;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "serial_number", nullable = false)
    private Drone drone;

}
