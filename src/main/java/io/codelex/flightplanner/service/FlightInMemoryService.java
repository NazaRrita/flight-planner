package io.codelex.flightplanner.service;

import io.codelex.flightplanner.domain.Airport;
import io.codelex.flightplanner.domain.PageResult;
import io.codelex.flightplanner.requests.AddFlightRequest;
import io.codelex.flightplanner.domain.Flight;
import io.codelex.flightplanner.repository.FlightInMemoryRepository;
import io.codelex.flightplanner.requests.SearchFlightsRequest;

import java.util.List;

public class FlightInMemoryService implements FlightService {

    private final FlightInMemoryRepository flightInMemoryRepository;

    public FlightInMemoryService(FlightInMemoryRepository flightRepository) {

        this.flightInMemoryRepository = flightRepository;
    }

    @Override
    public Flight addFlight(AddFlightRequest addFlightRequest) {
        return flightInMemoryRepository.addFlight(addFlightRequest);
    }

    @Override
    public void deleteFlight(Integer id) {
        flightInMemoryRepository.deleteFlight(id);
    }

    @Override
    public Flight fetchFlight(Integer id) {
        return flightInMemoryRepository.fetchFlight(id);
    }

    @Override
    public List<Airport> searchAirports(String phrase) {
        return flightInMemoryRepository.searchAirports(phrase);
    }

    @Override
    public PageResult<Flight> searchFlights(SearchFlightsRequest searchFlightsRequest) {
        return flightInMemoryRepository.searchFlights(searchFlightsRequest);
    }

    @Override
    public Flight findFlightById(Integer id) {
        return flightInMemoryRepository.fetchFlight(id);
    }

    @Override
    public void clearFlightList() {
        flightInMemoryRepository.clearFlightList();
    }


}
