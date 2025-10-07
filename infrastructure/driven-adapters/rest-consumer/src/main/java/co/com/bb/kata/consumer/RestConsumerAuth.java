package co.com.bb.kata.consumer;

import co.com.bb.kata.consumer.dto.response.UserResponse;
import co.com.bb.kata.model.exception.TechnicalException;
import co.com.bb.kata.model.exception.message.TechnicalExceptionMessage;
import co.com.bb.kata.model.gateway.RestConsumerAuthGateway;
import co.com.bb.kata.model.gateway.model.User;
import co.com.bb.kata.model.gateway.model.ValidateToken;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class RestConsumerAuth implements RestConsumerAuthGateway {

    private final String baseUrl;
    private final OkHttpClient client;
    private final ObjectMapper mapper;

    public RestConsumerAuth(@Value("${adapter.restconsumer.url}") String baseUrl,
                            OkHttpClient client,
                            ObjectMapper mapper) {
        this.baseUrl = baseUrl;
        this.client = client;
        this.mapper = mapper;

        this.mapper.registerModule(new JavaTimeModule());
        this.mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        this.mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    @Override
    @CircuitBreaker(name = "getUserById", fallbackMethod = "getUserByIdFallback")
    public User getUserById(Long userId) {
        String fullUrl = String.format("%s/user/%d", baseUrl, userId);
        log.info("[REST-AUTH] Sending GET request to {}", fullUrl);

        try {
            Request request = new Request.Builder()
                    .url(fullUrl)
                    .get()
                    .addHeader("Content-Type", "application/json")
                    .build();

            try (Response response = client.newCall(request).execute()) {
                if (response.isSuccessful() && response.body() != null) {
                    String json = response.body().string();
                    UserResponse userResponse = mapper.readValue(json, UserResponse.class);
                    return toDomain(userResponse);
                } else {
                    log.error("[REST-AUTH] Error response: {}", response);
                    throw new TechnicalException(TechnicalExceptionMessage.REST_CONSUMER_ERROR);
                }
            }
        } catch (Exception ex) {
            log.error("[REST-AUTH] Exception calling Auth service: {}", ex.getMessage(), ex);
            throw new TechnicalException(TechnicalExceptionMessage.REST_CONSUMER_ERROR);
        }
    }

    private User toDomain(UserResponse response) {
        return User.builder()
                .id(response.getId())
                .firstName(response.getFirstName())
                .lastName(response.getLastName())
                .identityDocument(response.getIdentityDocument())
                .phone(response.getPhone())
                .roleId(response.getRoleId())
                .roleName(response.getRoleName())
                .build();
    }
    public User getUserByIdFallback(Long userId, Throwable throwable) {
        log.error("[REST-AUTH] Fallback ejecutado para getUserById({}, {}): {}",
                userId, throwable.getClass().getSimpleName(), throwable.getMessage());
        return null; // o puedes devolver un User vacío si lo prefieres
    }


    @Override
    @CircuitBreaker(name = "validateToken", fallbackMethod = "validateTokenFallback")
    public ValidateToken validateToken(String token) {
        String fullUrl = String.format("%s/validate", baseUrl);
        log.info("[REST-AUTH] Validating token via {}", fullUrl);

        try {
            Request request = new Request.Builder()
                    .url(fullUrl)
                    .get()
                    .addHeader(HttpHeaders.AUTHORIZATION, token.startsWith("Bearer ") ? token : "Bearer " + token)
                    .build();

            try (Response response = client.newCall(request).execute()) {
                if (response.isSuccessful() && response.body() != null) {
                    String json = response.body().string();
                    return mapper.readValue(json, ValidateToken.class);
                } else if (response.code() == 401) {
                    log.warn("[REST-AUTH] Invalid or expired token");
                    return ValidateToken.builder().valid(false).build();
                } else {
                    log.error("[REST-AUTH] Unexpected response {}", response.code());
                    throw new TechnicalException(TechnicalExceptionMessage.REST_CONSUMER_ERROR);
                }
            }
        } catch (Exception e) {
            log.error("[REST-AUTH] Exception validating token: {}", e.getMessage(), e);
            throw new TechnicalException(TechnicalExceptionMessage.REST_CONSUMER_ERROR);
        }
    }

    public ValidateToken validateTokenFallback(String token, Throwable throwable) {
        log.error("[REST-AUTH] Fallback triggered: {}", throwable.getMessage());
        return ValidateToken.builder().valid(false).build();
    }
}