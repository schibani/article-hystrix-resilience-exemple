package fr.soat.hystrix.resource;

import fr.soat.hystrix.model.RemoteItinerary;
import fr.soat.hystrix.model.RemoteItineraryResponse;
import fr.soat.hystrix.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Component
@Path("itineraries/carRemote")
public class CarRemoteResource {

    @Autowired
    private CarService carService;

    @GET
    @Path("find")
    @Produces(MediaType.APPLICATION_JSON)
    public RemoteItineraryResponse getItineraries(@QueryParam("from") String from, @QueryParam("to") String to) {
        List<RemoteItinerary> itineraries = carService.getItineraries(from, to);
        RemoteItineraryResponse itineraryResponse = new RemoteItineraryResponse(itineraries);
        return itineraryResponse;
    }
}
