package aero.smart4aviation.Flight.assessment.repository;

import aero.smart4aviation.Flight.assessment.model.entity.Cargo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface CargoRepository extends JpaRepository<Cargo, Long> {
    Optional<Cargo> findByFlightId(Long flightId);
}
