package com.revature.models;

public class Order {
    private int orderId;
    private int userId;
    private float totalPrice;
    private Status status;
    private String timestamp;

    public Order(int orderId, int userId, float totalPrice, Status status, String timestamp) {
        this.orderId = orderId;
        this.userId = userId;
        this.totalPrice = totalPrice;
        this.status = status;
        this.timestamp = timestamp;
    }

    public Order(int orderId, int userId, float totalPrice, Status status) {
        this.orderId = orderId;
        this.userId = userId;
        this.totalPrice = totalPrice;
        this.status = status;
    }

    public int getOrderId() {
        return orderId;
    }

    public int getUserId() {
        return userId;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public Status getStatus() {
        return status;
    }

    public String getTimestamp() {
        return timestamp;
    }
}
