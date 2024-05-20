package io.codelex.flightplanner.controllers;

import io.codelex.flightplanner.requests.AddFlightRequest;
import io.codelex.flightplanner.domain.Flight;
import io.codelex.flightplanner.service.AdminService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Validated
@RequestMapping("/admin-api")
public class AdminFlightController {

    private final AdminService adminService;

    public AdminFlightController(AdminService flightService) {
        this.adminService = flightService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping("/flights")
    public synchronized Flight addFlight(@Valid @RequestBody AddFlightRequest addFlightRequest) {
        return adminService.addFlight(addFlightRequest);
    }
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/flights/{id}")
    public synchronized void deleteFlight(@PathVariable Integer id){
        adminService.deleteFlight(id);
    }
    @GetMapping("/flights/{id}")
    public Flight fetchFlight(@PathVariable Integer id){
        return adminService.fetchFlight(id);
    }

}
