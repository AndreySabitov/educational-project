package ru.sabitov.config;

import io.github.resilience4j.bulkhead.ThreadPoolBulkheadConfig;
import io.github.resilience4j.bulkhead.ThreadPoolBulkheadRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;

@Configuration
public class BulkheadRegistryConfig {

    @Bean
    public ThreadPoolBulkheadRegistry threadPoolBulkheadRegistry() {
        ThreadPoolBulkheadConfig config = ThreadPoolBulkheadConfig.custom()
                .maxThreadPoolSize(10)
                .coreThreadPoolSize(2)
                .queueCapacity(10)
                .writableStackTraceEnabled(true)
                .build();

        return ThreadPoolBulkheadRegistry.of(Collections.singletonMap("bookServiceBulkhead", config));
    }
}