package aero.smart4aviation.Flight.assessment.controller;

import aero.smart4aviation.Flight.assessment.model.response.AirportStatsResponse;
import aero.smart4aviation.Flight.assessment.model.response.FlightWeightResponse;
import aero.smart4aviation.Flight.assessment.service.FlightServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;


@RunWith(MockitoJUnitRunner.class)

public class FlightControllerTest {
    @InjectMocks
    private FlightController flightController;

    @Mock
    private FlightServiceImpl flightService;

    @Test
    public void shouldReturnWeightsForRequestedFlight() {
        Integer flightNumber = 6704;
        String date = "2023-01-01";

        int cargoWeight = 100;
        int baggageWeight = 50;
        int totalWeight = cargoWeight + baggageWeight;


        FlightWeightResponse response = flightController.getFlightWeight(flightNumber, date);


        assertThat(response, notNullValue());
        assertThat(response.getCargoWeight(), equalTo(cargoWeight));
        assertThat(response.getBaggageWeight(), equalTo(baggageWeight));
        assertThat(response.getTotalWeight(), equalTo(totalWeight));
    }

    @Test
    public void shouldReturnStatisticsForRequestedAirport() {
        String iataCode = "ANC";
        String date = "2018-11-25T10:03:45 -04:-30";

        int departingFlights = 3;
        int arrivingFlights = 5;
        int arrivingBaggage = 100;
        int departingBaggage = 75;

        AirportStatsResponse response = flightController.getAirportStats(iataCode, date);

        assertThat(response, notNullValue());
        assertThat(response.getDepartingFlightCount(), equalTo(departingFlights));
        assertThat(response.getArrivingFlightCount(), equalTo(arrivingFlights));
        assertThat(response.getArrivingBaggageCount(), equalTo(arrivingBaggage));
        assertThat(response.getDepartingBaggageCount(), equalTo(departingBaggage));
    }
}