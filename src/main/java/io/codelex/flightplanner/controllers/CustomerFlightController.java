package io.codelex.flightplanner.controllers;

import io.codelex.flightplanner.domain.Airport;
import io.codelex.flightplanner.domain.Flight;
import io.codelex.flightplanner.domain.PageResult;
import io.codelex.flightplanner.requests.SearchFlightsRequest;
import io.codelex.flightplanner.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CustomerFlightController {

    private final CustomerService customerService;

    public CustomerFlightController(CustomerService customerService) {
        this.customerService = customerService;
    }
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/airports")
    public List<Airport> searchAirports(String search) {
        return customerService.searchAirports(search);
    }
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/flights/search")
    public PageResult<Flight> searchFlights(@Valid @RequestBody SearchFlightsRequest searchFlightsRequest) {
        if (searchFlightsRequest.getFrom() == null || searchFlightsRequest.getTo() == null || searchFlightsRequest.getDepartureDate() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        return customerService.searchFlights(searchFlightsRequest);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/flights/{id}")
    public Flight findFlightById(@PathVariable ("id") Integer id) {
        return customerService.findFlightById(id);
    }

}
