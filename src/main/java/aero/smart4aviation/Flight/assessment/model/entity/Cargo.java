package aero.smart4aviation.Flight.assessment.model.entity;

 import lombok.*;

 import javax.persistence.*;
 import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Cargo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    @Column
    private Long flightId;
    @OneToMany(cascade = CascadeType.ALL)
    @Column
    private List<BaggageItem> baggage;
    @OneToMany(cascade = CascadeType.ALL)
    @Column
    private List<CargoItem> cargo;
}
