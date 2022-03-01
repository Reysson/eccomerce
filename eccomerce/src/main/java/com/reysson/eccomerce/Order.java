package com.reysson.eccomerce;

import java.math.BigDecimal;

public class Order {

    private final String userId, orderId;
    private final BigDecimal value;

    public Order(String userId, String orderId, BigDecimal value) {
        this.userId = userId;
        this.orderId = orderId;
        this.value = value;
    }

    public String getUserId() {
        return userId;
    }

    public String getOrderId() {
        return orderId;
    }

    public BigDecimal getValue() {
        return value;
    }
}
