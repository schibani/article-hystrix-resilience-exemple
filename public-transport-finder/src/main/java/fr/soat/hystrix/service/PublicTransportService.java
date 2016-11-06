package fr.soat.hystrix.service;

import com.google.common.collect.Lists;
import fr.soat.hystrix.model.RemoteItinerary;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.List;

@Component
public class PublicTransportService {
    public List<RemoteItinerary> getItineraries(String from, String to) {
        List<RemoteItinerary> itineraries = Lists.newArrayList();
        itineraries.add(buildItinerary(ZonedDateTime.now(), from, to, "Bus", "170"));
        itineraries.add(buildItinerary(ZonedDateTime.now().plusMinutes(30), from, to, "Metro", "14"));
        return itineraries;
    }

    private RemoteItinerary buildItinerary(ZonedDateTime departureTime, String from, String to, String mode, String line) {
        RemoteItinerary.ItineraryBuilder builder = RemoteItinerary.builder();
        return builder.setTransportMode(mode)
                .setLine(line)
                .setDeparturePoint("Arrêt " + from)
                .setArrivalAPoint("Arrêt " + to)
                .setDepartureTime(departureTime)
                .setArrivalTime(ZonedDateTime.now().plusMinutes(45))
                .build();
    }
}
