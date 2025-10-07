package co.com.bb.kata.model.gateway;

import co.com.bb.kata.model.gateway.model.User;
import co.com.bb.kata.model.gateway.model.ValidateToken;

public interface RestConsumerAuthGateway {

    public User getUserById(Long userId);
    ValidateToken validateToken(String token);
}