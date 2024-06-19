package io.codelex.flightplanner.service;

import io.codelex.flightplanner.domain.Airport;
import io.codelex.flightplanner.domain.Flight;
import io.codelex.flightplanner.domain.PageResult;
import io.codelex.flightplanner.requests.AddFlightRequest;
import io.codelex.flightplanner.requests.SearchFlightsRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FlightService {
    Flight addFlight(AddFlightRequest addFlightRequest);

    void deleteFlight(Integer id);

    Flight fetchFlight(Integer id);

    List<Airport> searchAirports(String phrase);

    PageResult<Flight> searchFlights(SearchFlightsRequest searchFlightsRequest);

    Flight findFlightById(Integer id);

    void clearFlightList();

}
