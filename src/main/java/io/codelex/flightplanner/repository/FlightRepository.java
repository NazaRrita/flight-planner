package io.codelex.flightplanner.repository;

import io.codelex.flightplanner.domain.Airport;
import io.codelex.flightplanner.domain.PageResult;
import io.codelex.flightplanner.requests.AddFlightRequest;
import io.codelex.flightplanner.domain.Flight;
import io.codelex.flightplanner.requests.SearchFlightsRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class FlightRepository {
    private List<Flight> flightList = new ArrayList<>();

    public Flight addFlight(AddFlightRequest addFlightRequest) {
        Integer id = flightList.size() + 1;
        Flight flight = new Flight(id, addFlightRequest);
        if (isTheSameFlight(flight)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        } else if (isFromTheSameAsToAirport(flight)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        } else if (flight.getArrivalTime().isBefore(flight.getDepartureTime())
                || flight.getArrivalTime().equals(flight.getDepartureTime())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        } else {
            flightList.add(flight);
            return flight;
        }
    }

    public void deleteFlight(Integer id){
        flightList.removeIf(flight -> flight.getId().equals(id));
    }

    public Flight fetchFlight(Integer id){
        return flightList.stream()
                .filter(flight -> flight.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public boolean isTheSameFlight(Flight flight) {
        return flightList.stream().anyMatch(currentFlight -> currentFlight.getFrom().equals(flight.getFrom())
                && currentFlight.getTo().equals(flight.getTo())
                && currentFlight.getCarrier().equals(flight.getCarrier())
                && currentFlight.getDepartureTime().equals(flight.getDepartureTime())
                && currentFlight.getArrivalTime().equals(flight.getArrivalTime()));
    }

    public boolean isFromTheSameAsToAirport(Flight flight) {
        return flight.getFrom().equals(flight.getTo());
    }

    public List <Airport> searchAirports(String phrase){
        return flightList.stream().map(flight -> flight.getFrom())
                .filter(airport -> airport.getAirport().toLowerCase().trim().contains(phrase.toLowerCase().trim())
                || airport.getCity().toLowerCase().trim().contains(phrase.toLowerCase().trim())
                || airport.getCountry().toLowerCase().trim().contains(phrase.toLowerCase().trim())).toList();
    }

    public PageResult<Flight> searchFlights(SearchFlightsRequest searchFlightsRequest){
        if (searchFlightsRequest.getFrom().equals(searchFlightsRequest.getTo())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        int page = 1;
        List<Flight> searchedFlights = flightList.stream()
                .filter(flight -> flight.getFrom().getAirport().equalsIgnoreCase(searchFlightsRequest.getFrom())
                && flight.getTo().getAirport().equalsIgnoreCase(searchFlightsRequest.getTo())
                && flight.getDepartureTime().toLocalDate().isEqual(LocalDate.parse(searchFlightsRequest.getDepartureDate())))
                .toList();
        if(searchedFlights.isEmpty()){
            page = 0;
        }

        return new PageResult<>(page, searchedFlights.size(), searchedFlights);

    }
    public Flight findFlightById(Integer id){
        return flightList.stream()
                .filter(flight -> flight.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }


    public void clearFlightList() {
        flightList.clear();
    }
}
