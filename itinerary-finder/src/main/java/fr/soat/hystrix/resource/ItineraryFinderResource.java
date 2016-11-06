package fr.soat.hystrix.resource;

import com.google.common.collect.Lists;
import fr.soat.hystrix.microservice.CarFinderService;
import fr.soat.hystrix.microservice.PublicTransportFinderService;
import fr.soat.hystrix.model.Itinerary;
import fr.soat.hystrix.model.ItineraryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Component
@Path("/itineraries")
public class ItineraryFinderResource {

    @Autowired
    CarFinderService carFinderService;

    @Autowired
    PublicTransportFinderService publicTransportFinderService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("find/{from}/{to}")
    public Response findItineraries(@PathParam("from") String from, @PathParam("to") String to) {
        List<Itinerary> itineraries = Lists.newArrayList();
        final ItineraryResponse carResponse = carFinderService.search(from, to);
        final List<Itinerary> carItineraries = carResponse.getItineraries();
        final ItineraryResponse ptResponse = publicTransportFinderService.search(from, to);
        final List<Itinerary> pTransportItineraries = ptResponse.getItineraries();

        itineraries.addAll(carItineraries);
        itineraries.addAll(pTransportItineraries);
        boolean partialResponse = carResponse.isPartialResponse() || ptResponse.isPartialResponse();
        ItineraryResponse itineraryResponse = new ItineraryResponse(itineraries, partialResponse);
        Response response = Response.ok().entity(itineraryResponse).build();
        return response;
    }
}
