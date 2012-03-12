package no.kantega.kriska.cafe;

import org.springframework.integration.annotation.Gateway;

public interface Cafe {

    @Gateway(requestChannel="orders")
    void placeOrder(Order order);

}
