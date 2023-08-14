package aero.smart4aviation.Flight.assessment.service;

import aero.smart4aviation.Flight.assessment.model.response.AirportStatsResponse;
import aero.smart4aviation.Flight.assessment.model.response.FlightWeightResponse;

import java.time.LocalDate;

public interface FlightService {
    public FlightWeightResponse getFlightWeight(Integer flightNumber, String date);
    public AirportStatsResponse getAirportStats(String airportCode, String date);
}
