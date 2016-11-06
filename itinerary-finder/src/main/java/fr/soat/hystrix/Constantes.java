package fr.soat.hystrix;

import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixThreadPoolKey;

public interface Constantes {

    String CAR_REMOTE_URI = "http://localhost:8090";
    String PT_REMOTE_URI = "http://localhost:8091";
    String CAR_JERSEY_PATH = "itineraries/carRemote/find";
    String PT_JERSEY_PATH = "itineraries/publicTransportRemote/find";

    HystrixCommandGroupKey CAR_HYSTRIX_COMMAND_GROUP_KEY = HystrixCommandGroupKey.Factory.asKey("Car");
    HystrixCommandKey CAR_HYSTRIX_COMMAND_KEY = HystrixCommandKey.Factory.asKey("Car");
    HystrixThreadPoolKey CAR_HYSTRIX_POOL_KEY = HystrixThreadPoolKey.Factory.asKey("Car");

    HystrixCommandGroupKey PT_HYSTRIX_COMMAND_GROUP_KEY = HystrixCommandGroupKey.Factory.asKey("PublicTransport");
    HystrixCommandKey PT_HYSTRIX_COMMAND_KEY = HystrixCommandKey.Factory.asKey("PublicTransport");
    HystrixThreadPoolKey PT_HYSTRIX_POOL_KEY = HystrixThreadPoolKey.Factory.asKey("PublicTransport");
}
