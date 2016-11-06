package fr.soat.hystrix.microservice;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.HystrixThreadPoolProperties;
import com.netflix.hystrix.exception.HystrixRuntimeException;
import fr.soat.hystrix.Constantes;
import fr.soat.hystrix.config.ClientJersey;
import fr.soat.hystrix.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.util.ArrayList;
import java.util.List;

import static fr.soat.hystrix.Constantes.*;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON_TYPE;

@Component
public class CarFinderService {

    @Autowired
    ClientJersey clientJersey;

    public ItineraryResponse search(String from, String to) {
        final ItineraryResponse itineraryResponse;
        Response response = null;
        CarHystrixCommand hystrixCommand = null;
        try {

            UriBuilder uriBuilder = getUriBuilder(from, to);
            final WebTarget target = clientJersey.getClient().target(uriBuilder);
            final Invocation.Builder request = target.request(APPLICATION_JSON_TYPE);
            hystrixCommand = new CarHystrixCommand(request);
            response = hystrixCommand.execute();
        } catch (HystrixRuntimeException e) {
            if (hystrixCommand != null) {
                hystrixCommand.closeConnection();
            }
            switch (e.getFailureType()) {
                case TIMEOUT:
                    throw new RemoteCallException(RemoteCallException.ExceptionType.TIMEOUT, hystrixCommand.getExecutionTimeInMilliseconds(), e);
                case SHORTCIRCUIT:
                    throw new RemoteCallException(RemoteCallException.ExceptionType.HYSTRIX_OPEN_CIRCUIT, hystrixCommand.getExecutionTimeInMilliseconds(), e);
                case REJECTED_THREAD_EXECUTION:
                    throw new RemoteCallException(RemoteCallException.ExceptionType.HYSTRIX_REJECTED_THREAD_EXECUTION, hystrixCommand.getExecutionTimeInMilliseconds(), e);
                default:
                    throw new RemoteCallException(RemoteCallException.ExceptionType.OTHER, hystrixCommand.getExecutionTimeInMilliseconds(), e);
            }
        }

        if (response.getEntity() instanceof ItineraryResponse) {
            itineraryResponse = (ItineraryResponse) response.getEntity();
        } else {
            itineraryResponse = adapteRemoteResponse(response.readEntity(RemoteItineraryResponse.class));
        }
        return itineraryResponse;
    }

    private UriBuilder getUriBuilder(String from, String to) {
        return UriBuilder.fromUri(CAR_REMOTE_URI)
                .path(Constantes.CAR_JERSEY_PATH)
                .queryParam("from", from)
                .queryParam("to", to);
    }

    private ItineraryResponse adapteRemoteResponse(RemoteItineraryResponse remoteResponse) {
        List<Itinerary> itineraries = adapteRemoteItineraries(remoteResponse.getItineraries());
        ItineraryResponse response = new ItineraryResponse(itineraries);
        return response;
    }

    private List<Itinerary> adapteRemoteItineraries(List<RemoteItinerary> remoteItineraries) {

        List<Itinerary> itineraries = new ArrayList<>();
        remoteItineraries.stream().forEach(iti -> {
            final Itinerary itinerary = Itinerary.builder().setLine(iti.getLine())
                    .setDeparturePoint(iti.getDeparturePoint())
                    .setArrivalAPoint(iti.getArrivalPoint())
                    .setDepartureTime(iti.getDepartureTime())
                    .setArrivalTime(iti.getArrivalTime())
                    .setDistance(iti.getDistance())
                    .setTransportMode(iti.getTransportMode())
                    .build();
            itineraries.add(itinerary);
        });

        return itineraries;
    }

    private class CarHystrixCommand extends HystrixCommand<Response> {

        private final Invocation.Builder request;

        private Response response = null;

        public CarHystrixCommand(Invocation.Builder request) {
            super(
                    Setter.withGroupKey(CAR_HYSTRIX_COMMAND_GROUP_KEY)
                            .andCommandKey(CAR_HYSTRIX_COMMAND_KEY)
                            .andThreadPoolKey(CAR_HYSTRIX_POOL_KEY)
                            .andThreadPoolPropertiesDefaults(HystrixThreadPoolProperties.Setter()
                                    .withCoreSize(10))
                            .andCommandPropertiesDefaults(HystrixCommandProperties.Setter()
                                    .withExecutionTimeoutInMilliseconds(2000)
                                    .withCircuitBreakerRequestVolumeThreshold(5)
                                    .withCircuitBreakerErrorThresholdPercentage(50)
                                    .withMetricsRollingStatisticalWindowInMilliseconds(10000)
                                    .withCircuitBreakerSleepWindowInMilliseconds(5000)));

            this.request = request;
        }

        @Override
        protected Response run() throws Exception {
            response = request.get();
            return response;
        }

        @Override
        protected Response getFallback() {
            boolean partialResponse = true;
            return Response.ok(new ItineraryResponse(partialResponse)).build();
        }

        public void closeConnection() {
            if (response != null) {
                response.close();
            }
        }
    }
}
