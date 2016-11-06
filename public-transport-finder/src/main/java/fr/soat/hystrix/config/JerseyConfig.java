package fr.soat.hystrix.config;

import fr.soat.hystrix.resource.PublicTransportRemoteResource;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

@Component
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        this.registerEndpoints();
    }

    private void registerEndpoints() {
        this.register(PublicTransportRemoteResource.class);
        this.register(JacksonMapper.class);
    }

}