package ru.sabitov.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import ru.sabitov.filters.BulkheadFilter;

@Configuration
public class BookServiceRouteConfig {
    private final BulkheadFilter bulkheadFilter;

    public BookServiceRouteConfig(BulkheadFilter bulkheadFilter) {
        this.bulkheadFilter = bulkheadFilter;
    }

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("book_service_route", r -> r.path("/library/v1/books/**")
                        .filters(f -> f
                                .filter(bulkheadFilter)
                                .rewritePath("/library/v1/books?(?<segment>.*)", "/books$\\{segment}")
                                .circuitBreaker(c -> c.setName("bookCircuitBreaker").setFallbackUri("forward:/bookServiceFallback"))
                                .retry(c -> c
                                        .setRetries(3)
                                        .setMethods(HttpMethod.POST, HttpMethod.PUT, HttpMethod.DELETE)))
                        .uri("lb://BOOK-SERVICE"))
                .build();
    }
}
