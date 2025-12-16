package ru.sabitov.jwt.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;
import ru.sabitov.jwt.provider.JwtTokenProvider;
import ru.sabitov.jwt.provider.UserInfo;

@Component
public class JwtFilter implements WebFilter {
    private final JwtTokenProvider jwtTokenProvider;

    private static final String BEARER_PREFIX = "Bearer ";
    private static final Logger log = LoggerFactory.getLogger(JwtFilter.class);

    public JwtFilter(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    @NonNull
    public Mono<Void> filter(@NonNull ServerWebExchange exchange, @NonNull WebFilterChain chain) {
        var request = exchange.getRequest();

        String path = request.getPath().toString();
        if (path.startsWith("/api/auth") || path.startsWith("/actuator")) {
            return chain.filter(exchange);
        }

        String authHeader = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
        if (authHeader == null || !authHeader.startsWith(BEARER_PREFIX)) {
            log.info("Не добавили заголовок с токеном");
            exchange.getResponse().setStatusCode(HttpStatus.BAD_REQUEST);
            return Mono.empty();
        }

        String jwt = authHeader.substring(BEARER_PREFIX.length());
        if (!jwtTokenProvider.validateToken(jwt)) {
            log.info("Не прошли валидацию токена");
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return Mono.empty();
        }
        log.info("Прошли валидацию токена");
        UserInfo userInfo = jwtTokenProvider.getUserDetails(jwt);
        log.info("userInfo = {}", userInfo);
        Authentication authentication = new UsernamePasswordAuthenticationToken(userInfo.username(), null,
                userInfo.roles());
        var context = ReactiveSecurityContextHolder.withAuthentication(authentication);
        return chain.filter(exchange).contextWrite(context);
    }
}
