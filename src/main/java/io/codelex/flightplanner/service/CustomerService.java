package io.codelex.flightplanner.service;

import io.codelex.flightplanner.domain.Airport;
import io.codelex.flightplanner.domain.Flight;
import io.codelex.flightplanner.domain.PageResult;
import io.codelex.flightplanner.repository.FlightRepository;
import io.codelex.flightplanner.requests.SearchFlightsRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    private final FlightRepository flightRepository;

    public CustomerService(FlightRepository flightRepository){
        this.flightRepository = flightRepository;
    }

    public List<Airport> searchAirports(String phrase){
        return flightRepository.searchAirports(phrase);
    }

    public PageResult<Flight> searchFlights(SearchFlightsRequest searchFlightsRequest){
        return flightRepository.searchFlights(searchFlightsRequest);
    }

    public Flight findFlightById(Integer id){
        return flightRepository.fetchFlight(id);
    }
}
