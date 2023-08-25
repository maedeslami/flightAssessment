package aero.smart4aviation.Flight.assessment.service;

import aero.smart4aviation.Flight.assessment.model.response.AirportStatsResponse;
import aero.smart4aviation.Flight.assessment.model.response.FlightWeightResponse;

import java.time.LocalDateTime;

public interface FlightService {
     FlightWeightResponse getFlightWeight(Integer flightNumber, LocalDateTime date);
     AirportStatsResponse getAirportStats(String airportCode, LocalDateTime date);
}
