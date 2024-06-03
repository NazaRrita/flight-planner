package io.codelex.flightplanner.repository;

import io.codelex.flightplanner.domain.Airport;
import io.codelex.flightplanner.domain.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface FlightDbRepository extends JpaRepository<Flight, Integer> {


    @Query("SELECT DISTINCT f.from FROM Flight f WHERE LOWER(f.from.airport) LIKE CONCAT('%', :phrase, '%')\n" +
            "                                       OR LOWER(f.from.city) LIKE CONCAT('%', :phrase, '%')\n" +
            "                                       OR LOWER(f.from.country) LIKE CONCAT('%', :phrase, '%')")
    List<Airport> searchAirports(@Param("phrase") String phrase);

    @Query("Select f FROM Flight f WHERE f.from.airport = :fromAirport " +
            "AND f.to.airport = :toAirport " +
            "AND f.departureTime = :departureTime")
    List<Flight> searchFlights(@Param("fromAirport") String fromAirport,
                               @Param("toAirport") String toAirport,
                               @Param("departureTime") LocalDateTime departureTime);

    @Query("SELECT f FROM Flight f WHERE f.from.airport = :fromAirport " +
            "AND f.to.airport = :toAirport " +
            "AND f.carrier = :carrier " +
            "AND f.departureTime = :departureTime " +
            "AND f.arrivalTime = :arrivalTime ")
    List<Flight> searchFlightsByCriteria(String fromAirport, String toAirport, String carrier,
                                         LocalDateTime departureTime, LocalDateTime arrivalTime);
}
