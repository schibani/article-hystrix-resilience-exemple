package fr.soat.hystrix.model;

import java.util.ArrayList;
import java.util.List;

public class ItineraryResponse {

    private final List<Itinerary> itineraries;

    private boolean partialResponse; // indique si la réponse est partielle

    public ItineraryResponse() {
        itineraries = new ArrayList<>();
    }

    public ItineraryResponse(List<Itinerary> itineraries) {
        this.itineraries = itineraries;
    }

    public ItineraryResponse(boolean isPartial) {
        this();
        this.partialResponse = isPartial;
    }

    public ItineraryResponse(List<Itinerary> itineraries, boolean partialResponse) {
        this(itineraries);
        this.partialResponse = partialResponse;
    }

    public List<Itinerary> getItineraries() {
        return itineraries;
    }

    public boolean isPartialResponse() {
        return partialResponse;
    }
}
