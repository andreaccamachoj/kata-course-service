package co.com.bb.kata.model.gateway;

import co.com.bb.kata.model.gateway.model.User;

public interface RestConsumerAuthGateway {

    public User getUserById(Long userId);
}