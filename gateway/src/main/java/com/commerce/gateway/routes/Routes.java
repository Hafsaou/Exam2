package com.commerce.gateway.routes;
//

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.Customizer;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

//
import java.net.URI;
import java.time.Duration;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;

//
@Configuration
public class Routes {
    @Autowired
    private CircuitBreakerFactory circuitBreakerFactory;


    @Bean
    public Customizer<Resilience4JCircuitBreakerFactory> specificCustomConfiguration1() {

        TimeLimiterConfig timeLimiterConfig = TimeLimiterConfig.custom()
                .timeoutDuration(Duration.ofSeconds(2))
                .build();
        CircuitBreakerConfig circuitBreakerConfig = CircuitBreakerConfig.custom()
                .failureRateThreshold(50)
                .waitDurationInOpenState(Duration.ofMillis(1000))
                .slidingWindowSize(2)
                .build();

        return factory -> factory.configure(builder -> builder.circuitBreakerConfig(circuitBreakerConfig)
                .timeLimiterConfig(timeLimiterConfig).build(), "circuitBreaker");
    }
//
//
//    @Bean
//    public RouterFunction<ServerResponse>  orderServiceRoute() {
//        return RouterFunctions.route(
//                RequestPredicates.path("/api/clients"),
//                request -> ServerResponse.permanentRedirect(URI.create("http://localhost:8080/api/clients")).build()
//        );
//    }

//    @Bean
//    public RouterFunction<ServerResponse> orderServiceSwaggerRoute() {
//        return RouterFunctions.route(
//                RequestPredicates.path("/aggregate/order-service/v3/api-docs"),
//                request -> ServerResponse.permanentRedirect(URI.create("http://localhost:8082/api-docs")).build()
//        );
//    }
//    @Bean
//    public RouterFunction<ServerResponse> productServiceRoute() {
//        return route(
//                RequestPredicates.path("/product"),
//                request -> ServerResponse.permanentRedirect(URI.create("http://localhost:8080/product/all")).build()
//        ).filter(circuitBreaker("inventoryServiceSwaggerCircuitBreaker", URI.create("forward:/fallbackRoute")));
//    }
@Bean
public RouteLocator myRoutes(RouteLocatorBuilder builder) {
    return builder.routes()
            // Route for product service with a CircuitBreaker
            .route("productServiceRoute", r -> r
                    .path("/aggregate/product-service/v3/api-docs") // Match the path
                    .filters(f -> f
                            .circuitBreaker(config -> config
                                    .setName("ProductServiceSwaggerCircuitBreaker")
                                    .setFallbackUri("forward:/fallbackRoute")) // Circuit Breaker with fallback
                            .rewritePath("/aggregate/product-service/v3/api-docs", "/product/all")) // Rewrite path
                    .uri("http://localhost:8080")) // Downstream service
            .route("orderServiceRoute", r -> r
                    .path("/aggregate/order-service/v3/api-docs") // Match the path
                    .filters(f -> f
                            .circuitBreaker(config -> config
                                    .setName("OrderServiceSwaggerCircuitBreaker")
                                    .setFallbackUri("forward:/orderFallback")) // Circuit Breaker with fallback
                            .rewritePath("/aggregate/order-service/v3/api-docs", "/api/clients")) // Rewrite path
                    .uri("http://localhost:8082"))  
            .build();
}


    @Bean
    public RouterFunction<ServerResponse> fallbackRoute() {
        System.out.println("Ordeeeeeeeeeeeeeeeeer   unreahceaaaaaaaaaaaaable");
        return route(RequestPredicates.path("/fallbackRoute"),
                request -> ServerResponse.status(HttpStatus.SERVICE_UNAVAILABLE)
                        .bodyValue("Order Service Unavailable, please try again later")


        ) ;
    }

    @Bean
    public RouterFunction<ServerResponse> orderFallback() {
        System.out.println("unreahceaaaaaaaaaaaaable");
        return route(RequestPredicates.path("/fallbackRoute"),
                request -> ServerResponse.status(HttpStatus.SERVICE_UNAVAILABLE)
                        .bodyValue("Service Unavailable, please try again later")


        ) ;
    }


//    @Bean
//    public RouterFunction<ServerResponse> productServiceRoute(WebClient webClient) {
//        return RouterFunctions.route(
//                path("/product"),
//                request -> handleRequestWithCircuitBreaker(webClient, request)
//        );
//    }



//

//    @Bean
//    public RouterFunction<ServerResponse> productServiceSwaggerRoute() {
//        return route(
//                RequestPredicates.path("/aggregate/product-service/v3/api-docs"),
//                request -> ServerResponse.permanentRedirect(URI.create("http://localhost:8080/api-docs")).build()
//        );
//    }
//
//    @Bean
//    public RouterFunction<ServerResponse> inventoryServiceSwaggerRoute() {
//        return route(
//                RequestPredicates.path("/aggregate/inventory-service/v3/api-docs"),
//                request -> ServerResponse.permanentRedirect(URI.create("http://localhost:8082/api-docs")).build()
//        );
//    }

//    @Bean
//    @CircuitBreaker(name="hhhh", fallbackMethod = "fallbackRoute")
//    public RouterFunction<ServerResponse> inventoryServiceSwaggerRoute() {
//        return RouterFunctions.route(
//                RequestPredicates.path("/aggregate/inventory-service/v3/api-docs"),
//                request -> ServerResponse.permanentRedirect(URI.create("http://localhost:8082/api-docs"))
//                        .build()
//        );
//    }

//    Bean
//    public RouterFunction<ServerResponse> serviceRouteConfig() {
//        return RouterFunctions
//                .route()
//                .route(GatewayRequestPredicates.path("/api/service/**"), HandlerFunctions.http())
//                .before(BeforeFilterFunctions.stripPrefix(2)) // remove "/api/service/"
//                .filter(LoadBalancerFilterFunctions.lb("SERVICE-NAME"))
//                .filter(CircuitBreakerFilterFunctions.circuitBreaker("defaultCircuitBreaker"))
//                .build();
//    }




//
////    @Bean
////    public RouterFunction<ServerResponse> orderServiceSwaggerRoute() {
////        return GatewayRouterFunctions.route("order_service_swagger")
////                .route(RequestPredicates.path("/aggregate/order-service/v3/api-docs"), HandlerFunctions.http("http://localhost:8081"))
////                .filter(setPath("/api-docs"))
////                .build();
    //}
//
//
//
}