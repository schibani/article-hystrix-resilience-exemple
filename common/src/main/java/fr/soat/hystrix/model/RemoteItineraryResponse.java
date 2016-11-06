package fr.soat.hystrix.model;

import java.util.ArrayList;
import java.util.List;

public class RemoteItineraryResponse {

    private final List<RemoteItinerary> itineraries;

    public RemoteItineraryResponse() {
        itineraries = new ArrayList<>();
    }

    public RemoteItineraryResponse(List<RemoteItinerary> itineraries) {
        this.itineraries = itineraries;
    }

    public List<RemoteItinerary> getItineraries() {
        return itineraries;
    }
}
