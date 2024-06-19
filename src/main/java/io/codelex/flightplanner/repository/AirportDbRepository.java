package io.codelex.flightplanner.repository;

import io.codelex.flightplanner.domain.Airport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface AirportDbRepository extends JpaRepository<Airport, String> {
    @Query("SELECT a FROM Airport a WHERE UPPER(a.airport) LIKE CONCAT('%', :phrase, '%')" +
            "OR UPPER(a.city) LIKE CONCAT('%', :phrase, '%')" +
            "OR UPPER(a.country) LIKE CONCAT('%', :phrase, '%')")
    List<Airport> searchAirportByPhrase(@Param("phrase") String phrase);
}
