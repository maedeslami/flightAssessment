package aero.smart4aviation.Flight.assessment.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class AirportStatsResponse {
    private int departingFlightCount;
    private int arrivingFlightCount;
    private int arrivingBaggageCount;
    private int departingBaggageCount;


}
