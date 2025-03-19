package com.revature.models;

public class Product {
    private int productId;
    private String name;
    private String description;
    private float price;
    private int stock;

    private int counter = 1;

    public Product(String name, String description, int price, int stock) {
        this.productId = counter;
        counter++;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
    }
    public Product (){}

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getProductId() {
        return productId;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
