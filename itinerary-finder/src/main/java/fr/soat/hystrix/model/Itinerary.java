package fr.soat.hystrix.model;

import java.time.ZonedDateTime;

public class Itinerary {

    private String departurePoint;
    private String arrivalPoint;
    private String transportMode;
    private String line;
    private Double distance;
    private ZonedDateTime departureTime;
    private ZonedDateTime arrivalTime;

    private Itinerary(ItineraryBuilder builder) {
        this.departurePoint = builder.departurePoint;
        this.arrivalPoint = builder.arrivalPoint;
        this.departureTime = builder.departureTime;
        this.arrivalTime = builder.arrivalTime;
        this.transportMode = builder.transportMode;
        this.distance = builder.distance;
        this.line = builder.line;
    }

    public Itinerary() {
    }

    public static ItineraryBuilder builder() {
        return new ItineraryBuilder();
    }

    public String getDeparturePoint() {
        return departurePoint;
    }

    public String getArrivalPoint() {
        return arrivalPoint;
    }

    public String getTransportMode() {
        return transportMode;
    }

    public Double getDistance() {
        return distance;
    }

    public ZonedDateTime getDepartureTime() {
        return departureTime;
    }

    public ZonedDateTime getArrivalTime() {
        return arrivalTime;
    }

    public String getLine() {
        return line;
    }

    public static class ItineraryBuilder {

        private String departurePoint;
        private String arrivalPoint;
        private String transportMode;
        private String line;
        private Double distance;
        private ZonedDateTime departureTime;
        private ZonedDateTime arrivalTime;

        private ItineraryBuilder() {
        }

        public ItineraryBuilder setDeparturePoint(String departure) {
            this.departurePoint = departure;
            return this;
        }

        public ItineraryBuilder setArrivalAPoint(String arrival) {
            this.arrivalPoint = arrival;
            return this;
        }

        public ItineraryBuilder setDepartureTime(ZonedDateTime departureTime) {
            this.departureTime = departureTime;
            return this;
        }

        public ItineraryBuilder setArrivalTime(ZonedDateTime arrivalTime) {
            this.arrivalTime = arrivalTime;
            return this;
        }

        public ItineraryBuilder setTransportMode(String transportMode) {
            this.transportMode = transportMode;
            return this;
        }

        public ItineraryBuilder setDistance(Double distance) {
            this.distance = distance;
            return this;
        }

        public ItineraryBuilder setLine(String line) {
            this.line = line;
            return this;
        }

        public Itinerary build() {
            return new Itinerary(this);
        }
    }
}
