package aero.smart4aviation.Flight.assessment.model.entity;


import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class BaggageItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long baggageItemId;
    @Column
    private Long id;
    @Column
    private Integer  weight;
    @Column
    private String weightUnit;
    @Column
    private Integer  pieces;
    @ManyToOne
    @JoinColumn(name = "cargo_id")
    private Cargo cargo;
}
