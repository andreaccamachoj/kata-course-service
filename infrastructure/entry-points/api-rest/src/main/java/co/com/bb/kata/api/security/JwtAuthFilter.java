package co.com.bb.kata.api.security;

import co.com.bb.kata.model.gateway.RestConsumerAuthGateway;
import co.com.bb.kata.model.gateway.model.ValidateToken;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final RestConsumerAuthGateway restConsumerAuthGateway;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            unauthorized(response, "Missing or invalid Authorization header");
            return;
        }

        try {
            ValidateToken validateResponse = restConsumerAuthGateway.validateToken(authHeader);

            if (validateResponse == null || !validateResponse.isValid()) {
                unauthorized(response, "Invalid or expired token");
                return;
            }
            String rawToken = authHeader.replace("Bearer ", "");

            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(validateResponse, rawToken, Collections.emptyList());

            SecurityContextHolder.getContext().setAuthentication(authentication);

            log.debug("[COURSE-FILTER] User authenticated: {}", validateResponse.getUserId());

            filterChain.doFilter(request, response);

        } catch (Exception e) {
            log.error("[COURSE-FILTER] Error validating token: {}", e.getMessage(), e);
            unauthorized(response, "Error validating token");
        }
    }

    private void unauthorized(HttpServletResponse response, String message) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().write(String.format("{\"error\":\"%s\"}", message));
    }
}