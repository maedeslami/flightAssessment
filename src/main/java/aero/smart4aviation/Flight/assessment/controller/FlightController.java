package aero.smart4aviation.Flight.assessment.controller;

import aero.smart4aviation.Flight.assessment.model.response.AirportStatsResponse;
import aero.smart4aviation.Flight.assessment.model.response.FlightWeightResponse;
import aero.smart4aviation.Flight.assessment.service.FlightServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class FlightController {
    private final FlightServiceImpl flightService;

    @GetMapping("/flight")
    public FlightWeightResponse getFlightWeight(@RequestParam Integer flightNumber, @RequestParam String date) {
        return flightService.getFlightWeight(flightNumber, date);
    }

    @GetMapping("/airport")
    public AirportStatsResponse getAirportStats(@RequestParam String airportCode, @RequestParam String date) {
        return flightService.getAirportStats(airportCode, date);
    }

    @PostMapping("/saveAllFlights")
    public void saveAllFlights(@RequestBody String jsonArray) {
        flightService.saveAllFlights(jsonArray);
    }

    @PostMapping("/saveAllCargos")
    public void saveAllCargos(@RequestBody String jsonArray) {
        flightService.saveAllCargos(jsonArray);
    }
}
