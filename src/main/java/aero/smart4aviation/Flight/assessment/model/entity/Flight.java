package aero.smart4aviation.Flight.assessment.model.entity;


import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "FLIGHT")
public class Flight implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    @Column
    private Long flightId;
    @Column
    private Integer flightNumber;
    @Column
    private String departureAirportIATACode;
    @Column
    private String arrivalAirportIATACode;
    @Column
    private String departureDate;


}
