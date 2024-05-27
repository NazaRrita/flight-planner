package io.codelex.flightplanner.service;

import io.codelex.flightplanner.repository.FlightRepository;
import org.springframework.stereotype.Service;

@Service
public class TestService {

    private final FlightRepository flightRepository;

    public TestService(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    public void clearFlightList() {
        flightRepository.clearFlightList();
    }
}
