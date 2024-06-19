package io.codelex.flightplanner.service;

import io.codelex.flightplanner.domain.Airport;
import io.codelex.flightplanner.domain.DateTimeConverter;
import io.codelex.flightplanner.domain.Flight;
import io.codelex.flightplanner.domain.PageResult;
import io.codelex.flightplanner.repository.AirportDbRepository;
import io.codelex.flightplanner.repository.FlightDbRepository;
import io.codelex.flightplanner.requests.AddFlightRequest;
import io.codelex.flightplanner.requests.SearchFlightsRequest;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;


public class FlightDbService implements FlightService {

    public FlightDbRepository flightDbRepository;
    public AirportDbRepository airportDbRepository;

    public FlightDbService(FlightDbRepository flightDbRepository, AirportDbRepository airportDbRepository) {
        this.flightDbRepository = flightDbRepository;
        this.airportDbRepository = airportDbRepository;
    }

    @Override
    @Transactional
    public Flight addFlight(AddFlightRequest addFlightRequest) {
        airportValidation(addFlightRequest);

        LocalDateTime departureTime = addFlightRequest.getDepartureTime();
        LocalDateTime arrivalTime = addFlightRequest.getArrivalTime();
        if (arrivalTime.isBefore(departureTime) || arrivalTime.isEqual(departureTime)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        checkIfFlightAlreadyExist(addFlightRequest);

        Flight flight = new Flight(addFlightRequest.getFrom(), addFlightRequest.getTo(),
                addFlightRequest.getCarrier(), departureTime, arrivalTime);
        airportDbRepository.save(flight.getFrom());
        airportDbRepository.save(flight.getTo());
        return flightDbRepository.save(flight);
    }

    public void airportValidation(AddFlightRequest addFlightRequest) {
        String airportFrom = addFlightRequest.getFrom().getAirport().trim();
        String airportTo = addFlightRequest.getTo().getAirport().trim();
        String cityFrom = addFlightRequest.getFrom().getCity().trim();
        String cityTo = addFlightRequest.getTo().getCity().trim();
        String countryFrom = addFlightRequest.getFrom().getCountry().trim();
        String countryTo = addFlightRequest.getTo().getCountry().trim();

        if (airportFrom.equalsIgnoreCase(airportTo) &&
                cityFrom.equalsIgnoreCase(cityTo) &&
                countryFrom.equalsIgnoreCase(countryTo)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    public void checkIfFlightAlreadyExist(AddFlightRequest request) {
        List<Flight> existingFlights = flightDbRepository.searchFlightDuplicates(
                request.getFrom().getAirport(),
                request.getTo().getAirport(),
                request.getCarrier(),
                request.getDepartureTime(),
                request.getArrivalTime()
        );

        if (!existingFlights.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }
    }

    @Override
    @Transactional
    public void deleteFlight(Integer id) {
        flightDbRepository.deleteById(id);
    }

    @Override
    public Flight fetchFlight(Integer id) {
        return flightDbRepository.findById(id).orElseThrow(()
                -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Override
    public List<Airport> searchAirports(String phrase) {
        return airportDbRepository.searchAirportByPhrase(phrase.toUpperCase().trim());
    }

    @Override
    public PageResult<Flight> searchFlights(SearchFlightsRequest searchFlightsRequest) {
        if (searchFlightsRequest.getFrom().equalsIgnoreCase(searchFlightsRequest.getTo())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        LocalDateTime departureTime =
                DateTimeConverter.dateValidationAndConvertToLocalDateTime(searchFlightsRequest.getDepartureDate());


        List<Flight> flights = flightDbRepository.searchFlights(
                searchFlightsRequest.getFrom(),
                searchFlightsRequest.getTo(),
                departureTime
        );
        Integer totalItems = Math.toIntExact(flightDbRepository.count());
        return new PageResult<>(0, totalItems, flights);

    }

    @Override
    public Flight findFlightById(Integer id) {
        return flightDbRepository.findById(id).orElseThrow(()
                -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Override
    @Transactional
    public void clearFlightList() {
        flightDbRepository.deleteAll();
    }

}
