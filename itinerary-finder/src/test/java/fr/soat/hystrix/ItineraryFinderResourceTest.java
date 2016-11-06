package fr.soat.hystrix;

import com.github.tomakehurst.wiremock.client.WireMock;
import org.junit.Test;

import javax.ws.rs.core.Response;

public class ItineraryFinderResourceTest {

    @Test
    public void test() {
        WireMock.stubFor(WireMock.any(WireMock.urlMatching("")).willReturn(
                WireMock.aResponse().withStatus(
                        Response.Status.OK.getStatusCode()).withBody("FlightResponseAsString")
                )
        );
    }

}
