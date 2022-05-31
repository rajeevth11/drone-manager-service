package api.drone.dto;


import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MedicationDTO
{
    private String name;
    private double weight;
    private String code;
    private String image;
    private String username;

}
