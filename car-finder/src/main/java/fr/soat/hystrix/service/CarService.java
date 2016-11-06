package fr.soat.hystrix.service;

import com.google.common.collect.Lists;
import fr.soat.hystrix.model.RemoteItinerary;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.List;

@Component
public class CarService {
    public List<RemoteItinerary> getItineraries(String from, String to) {
        List<RemoteItinerary> itineraries = Lists.newArrayList();
        itineraries.add(buildItinerary(ZonedDateTime.now(), from, to));
        return itineraries;
    }

    private RemoteItinerary buildItinerary(ZonedDateTime departureTime, String from, String to) {
        RemoteItinerary.ItineraryBuilder builder = RemoteItinerary.builder();
        return builder.setTransportMode("Car")
                .setDeparturePoint("Adresse " + from)
                .setArrivalAPoint("Adresse " + to)
                .setDepartureTime(departureTime)
                .setArrivalTime(ZonedDateTime.now().plusMinutes(45))
                .setDistance(5000.0)
                .build();
    }
}
