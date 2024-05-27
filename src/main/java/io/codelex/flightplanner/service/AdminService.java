package io.codelex.flightplanner.service;

import io.codelex.flightplanner.requests.AddFlightRequest;
import io.codelex.flightplanner.domain.Flight;
import io.codelex.flightplanner.repository.FlightRepository;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    private final FlightRepository flightRepository;

    public AdminService(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    public Flight addFlight(AddFlightRequest addFlightRequest){
        return flightRepository.addFlight(addFlightRequest);
    }

    public void deleteFlight(Integer id){
        flightRepository.deleteFlight(id);
    }
    public Flight fetchFlight(Integer id){
        return flightRepository.fetchFlight(id);
    }


}
