package io.codelex.flightplanner.requests;

import jakarta.validation.constraints.NotNull;

import java.util.Objects;

public class SearchFlightsRequest {
    @NotNull
    private String from;
    @NotNull
    private String to;
    @NotNull
    private String departureDate;

    public SearchFlightsRequest(String fromAirport, String toAirport, String departureDate) {
        this.from = fromAirport;
        this.to = toAirport;
        this.departureDate = departureDate;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SearchFlightsRequest that = (SearchFlightsRequest) o;
        return Objects.equals(from, that.from) && Objects.equals(to, that.to) && Objects.equals(departureDate, that.departureDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(from, to, departureDate);
    }

    @Override
    public String toString() {
        return "SearchFlightsRequest{" +
                "fromAirport='" + from + '\'' +
                ", toAirport='" + to + '\'' +
                ", departureDate='" + departureDate + '\'' +
                '}';
    }
}
