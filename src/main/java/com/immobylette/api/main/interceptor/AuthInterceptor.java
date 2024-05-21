package com.immobylette.api.main.interceptor;

import com.immobylette.api.main.config.AuthConfig;
import com.immobylette.api.main.exception.AuthMissingException;
import com.immobylette.api.main.exception.AuthWrongException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.lang.NonNullApi;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@AllArgsConstructor
public class AuthInterceptor implements HandlerInterceptor {
    private final AuthConfig authConfig;

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){
        String header = request.getHeader("X-Api-Key");
        if (header == null || header.isEmpty()) {
            throw new AuthMissingException();
        }
        if (!header.equals(authConfig.getApiKey())) {
            throw new AuthWrongException();
        }

        return true;
    }
}