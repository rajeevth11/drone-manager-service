package api.drone.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import net.minidev.json.annotate.JsonIgnore;

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
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties({"drone"})
public class Medication
{
    @Id
    String code;
    String name;
    double weight;
    String image;
    @Column(name = "modified_user")
    String username;
    @Column(name = "last_modified")
    Timestamp lastModified;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "serial_num", referencedColumnName = "serial_number", nullable = false)
    @JsonIgnore
    private Drone drone;

}
