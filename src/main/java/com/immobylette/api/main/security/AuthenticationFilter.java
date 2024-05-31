package com.immobylette.api.main.security;

import com.immobylette.api.main.config.AuthConfig;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@AllArgsConstructor
@Order(1)
public class AuthenticationFilter extends OncePerRequestFilter {
    private final AuthConfig authConfig;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String apiKey = request.getHeader("X-Api-Key");
        logger.error(String.format("Header : %s", request));
        logger.error(String.format(String.format("given key and exepct : %s / %s", apiKey, authConfig.getApiKey())));
        logger.error(apiKey == null);
        logger.error(apiKey.isEmpty());
        logger.error(apiKey == authConfig.getApiKey());
        logger.error(apiKey.equals(authConfig.getApiKey()));

        if (apiKey == null || apiKey.isEmpty() || apiKey != authConfig.getApiKey()){
            logger.error("Unauthorized");
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Access Denied");
            return;
        }

        filterChain.doFilter(request, response);
    }
}