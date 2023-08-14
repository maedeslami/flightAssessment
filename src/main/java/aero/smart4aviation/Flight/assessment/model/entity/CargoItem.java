package aero.smart4aviation.Flight.assessment.model.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class CargoItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long cargoItemId;
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
