package aero.smart4aviation.Flight.assessment.service;

import aero.smart4aviation.Flight.assessment.model.entity.BaggageItem;
import aero.smart4aviation.Flight.assessment.model.entity.Cargo;
import aero.smart4aviation.Flight.assessment.model.entity.CargoItem;
import aero.smart4aviation.Flight.assessment.model.entity.Flight;
import aero.smart4aviation.Flight.assessment.model.response.AirportStatsResponse;
import aero.smart4aviation.Flight.assessment.model.response.FlightWeightResponse;
import aero.smart4aviation.Flight.assessment.repository.CargoRepository;
import aero.smart4aviation.Flight.assessment.repository.FlightRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class FlightServiceImpl implements FlightService {

    private final FlightRepository flightRepository;
    private final CargoRepository cargoRepository;
    @Override
    public FlightWeightResponse getFlightWeight(Integer flightNumber, String departureDate) {

        List<Flight> flights = flightRepository.findByFlightNumberAndDepartureDate(flightNumber, departureDate);

        if (flights.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Flight not found");
        }

        Flight flight = flights.get(0);
        Optional<Cargo> optionalCargo = cargoRepository.findByFlightId(flight.getFlightId());

        if (!optionalCargo.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cargo not found");
        }

        Cargo cargo = optionalCargo.get();
        int baggageWeight = cargo.getBaggage().stream().mapToInt(BaggageItem::getWeight).sum();
        int cargoWeight = cargo.getCargo().stream().mapToInt(CargoItem::getWeight).sum();
        int totalWeight = baggageWeight + cargoWeight;

        return new FlightWeightResponse(baggageWeight, cargoWeight, totalWeight);
    }

    @Override
    public AirportStatsResponse getAirportStats(String airportCode, String departureDate) {

        List<Flight> departingFlights = flightRepository.findByDepartureAirportIATACodeAndDepartureDate(airportCode, departureDate);
        List<Flight> arrivingFlights = flightRepository.findByArrivalAirportIATACodeAndDepartureDate(airportCode, departureDate);

        int departingFlightCount = departingFlights.size();
        int arrivingFlightCount = arrivingFlights.size();
        int arrivingBaggageCount = arrivingFlights.stream().mapToInt(flight -> cargoRepository.findByFlightId(flight.getFlightId()).map(cargo -> cargo.getBaggage().size()).orElse(0)).sum();
        int departingBaggageCount = departingFlights.stream().mapToInt(flight -> cargoRepository.findByFlightId(flight.getFlightId()).map(cargo -> cargo.getBaggage().size()).orElse(0)).sum();
        return new AirportStatsResponse(departingFlightCount, arrivingFlightCount, arrivingBaggageCount, departingBaggageCount);
    }

    public void saveAllFlights(String jsonArray) {
        try {
            List<Flight> flightsList = Arrays.asList(new ObjectMapper().readValue(jsonArray, Flight[].class));
            flightRepository.saveAll(flightsList);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    public void saveAllCargos(String jsonArray) {
        try {
            List<Cargo> cargosList = Arrays.asList(new ObjectMapper().readValue(jsonArray, Cargo[].class));
            cargoRepository.saveAll(cargosList);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
