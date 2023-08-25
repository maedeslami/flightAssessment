package aero.smart4aviation.Flight.assessment.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class FlightWeightResponse {
    private int cargoWeight;
    private int baggageWeight;
    private int totalWeight;
}
