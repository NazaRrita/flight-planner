package io.codelex.flightplanner.controllers;

import io.codelex.flightplanner.requests.AddFlightRequest;
import io.codelex.flightplanner.domain.Flight;
import io.codelex.flightplanner.service.FlightService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@Validated
@RequestMapping("/admin-api")
public class AdminController {

    private final FlightService flightService;

    public AdminController(FlightService flightService) {
        this.flightService = flightService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping("/flights")
    public synchronized Flight addFlight(@Valid @RequestBody AddFlightRequest addFlightRequest) {
        return flightService.addFlight(addFlightRequest);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/flights/{id}")
    public synchronized void deleteFlight(@PathVariable Integer id) {
        flightService.deleteFlight(id);
    }

    @GetMapping("/flights/{id}")
    public Flight fetchFlight(@PathVariable Integer id) {
        return flightService.fetchFlight(id);
    }


}
