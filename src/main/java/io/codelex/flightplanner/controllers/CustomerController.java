
package io.codelex.flightplanner.controllers;

import io.codelex.flightplanner.domain.Airport;
import io.codelex.flightplanner.domain.Flight;
import io.codelex.flightplanner.domain.PageResult;
import io.codelex.flightplanner.requests.SearchFlightsRequest;
import io.codelex.flightplanner.service.FlightService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CustomerController {

    private final FlightService flightService;

    public CustomerController(FlightService flightService) {
        this.flightService = flightService;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/airports")
    public List<Airport> searchAirports(String search) {
        return flightService.searchAirports(search);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/flights/search")
    public PageResult<Flight> searchFlights(@Valid @RequestBody SearchFlightsRequest searchFlightsRequest) {
        if (searchFlightsRequest.getFrom() == null || searchFlightsRequest.getTo() == null || searchFlightsRequest.getDepartureDate() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        return flightService.searchFlights(searchFlightsRequest);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/flights/{id}")
    public Flight findFlightById(@PathVariable("id") Integer id) {
        Flight flight = flightService.findFlightById(id);
        if (flight == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return flight;
    }

}
