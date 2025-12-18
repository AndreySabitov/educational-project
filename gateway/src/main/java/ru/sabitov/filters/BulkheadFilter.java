package ru.sabitov.filters;

import io.github.resilience4j.bulkhead.ThreadPoolBulkhead;
import io.github.resilience4j.bulkhead.ThreadPoolBulkheadRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.concurrent.Callable;

@Component
public class BulkheadFilter implements GatewayFilter {
    private final ThreadPoolBulkhead bulkhead;

    private static final Logger log = LoggerFactory.getLogger(BulkheadFilter.class);

    public BulkheadFilter(ThreadPoolBulkheadRegistry bulkheadRegistry) {
        this.bulkhead = bulkheadRegistry.bulkhead("bookServiceBulkhead");
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        Callable<Mono<Void>> callable = () -> {
            log.info("Проходим через BulkheadFilter");
            return chain.filter(exchange);
        };

        return ThreadPoolBulkhead.decorateCallable(bulkhead, callable).get()
                .toCompletableFuture().join();
    }
}
