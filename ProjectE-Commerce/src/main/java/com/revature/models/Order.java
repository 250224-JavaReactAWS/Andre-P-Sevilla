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

    public Order() {

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

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
