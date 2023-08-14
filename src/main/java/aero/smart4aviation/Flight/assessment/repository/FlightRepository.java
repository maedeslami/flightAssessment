package aero.smart4aviation.Flight.assessment.repository;

import aero.smart4aviation.Flight.assessment.model.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {

    List<Flight> findByDepartureAirportIATACodeAndDepartureDate(String iataCode, String date);

    List<Flight> findByArrivalAirportIATACodeAndDepartureDate(String iataCode, String date);

    List<Flight> findByFlightNumberAndDepartureDate(Integer flightNumber, String departureDate);

}
