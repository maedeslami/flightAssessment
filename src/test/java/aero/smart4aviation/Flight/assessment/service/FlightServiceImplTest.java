package aero.smart4aviation.Flight.assessment.service;

import aero.smart4aviation.Flight.assessment.model.entity.Cargo;
import aero.smart4aviation.Flight.assessment.model.entity.Flight;
import aero.smart4aviation.Flight.assessment.model.response.AirportStatsResponse;
import aero.smart4aviation.Flight.assessment.model.response.FlightWeightResponse;
import aero.smart4aviation.Flight.assessment.repository.CargoRepository;
import aero.smart4aviation.Flight.assessment.repository.FlightRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class FlightServiceImplTest {

    @Mock
    private FlightRepository flightRepository;

    @Mock
    private CargoRepository cargoRepository;

    @InjectMocks
    private FlightServiceImpl flightService;

    public FlightServiceImplTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getFlightWeights_shouldReturnCorrectWeights() {
        // Mock flight data
        List<Flight> flights = TestData.generateFlights();
        when(flightRepository.findByFlightNumberAndDepartureDate(anyInt(), anyString())).thenReturn(flights);

        // Mock cargo data
        List<Cargo> cargoList = TestData.generateCargo();
        Mockito.doReturn(cargoList).when(cargoRepository.findByFlightId(anyLong()));

        // Test for flight weights
        Integer flightNumber = 6704;
        String departureDate = "2023-03-14";
        FlightWeightResponse expectedWeights = new FlightWeightResponse(1000, 2000, 3000);

        FlightWeightResponse result = flightService.getFlightWeight(flightNumber, departureDate);

        assertEquals(expectedWeights, result);
        verify(flightRepository, times(1)).findByFlightNumberAndDepartureDate(flightNumber, departureDate);
        verify(cargoRepository, times(1)).findByFlightId(flights.get(0).getFlightId());
    }

    @Test
    void getAirportStatistics_shouldReturnCorrectStatistics() {
        // Mock flight data
        List<Flight> flights = TestData.generateFlights();
        when(flightRepository.findByFlightNumberAndDepartureDate(anyInt(), anyString())).thenReturn(flights);

        // Mock cargo data
        List<Cargo> cargoList = TestData.generateCargo();
        Mockito.doReturn(cargoList).when(cargoRepository.findByFlightId(anyLong()));

        // Test for airport statistics
        String airportCode = "sea";
        String date = "2023-03-14";
        AirportStatsResponse expectedStatistics = new AirportStatsResponse(5, 5, 36, 36);

        AirportStatsResponse result = flightService.getAirportStats(airportCode, date);

        assertEquals(expectedStatistics, result);
        verify(flightRepository, times(1)).findByArrivalAirportIATACodeAndDepartureDate(airportCode, date);
        verify(flightRepository, times(1)).findByDepartureAirportIATACodeAndDepartureDate(airportCode, date);
        verify(cargoRepository, times(flights.size())).findByFlightId(anyLong());
    }
}

class TestData {
    public static List<Flight> generateFlights() {
        List<Flight> flights = new ArrayList<>();
        return flights;
    }

    public static List<Cargo> generateCargo() {
        List<Cargo> cargoList = new ArrayList<>();
        return cargoList;
    }
}