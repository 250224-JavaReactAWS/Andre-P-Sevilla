package com.revature.controllers;

import com.revature.services.BuyoutService;
import io.javalin.http.Context;

public class CartController {

    private final BuyoutService buyoutService;

    public CartController(BuyoutService buyoutService){
        this.buyoutService = buyoutService;
    }

    public void addItemToCart(Context ctx){

    }

    public void removeItemFromCart(Context ctx){

    }

    public void updateItemFromCart(Context ctx){

    }

    public void PlaceOrder(Context ctx){

    }

    public void getOrders(Context ctx){

    }

    public void updateOrder(Context ctx){

    }

    public void getStatusOrders(Context ctx){

    }
    /*
    post("/add-product");
    post("/remove-product");
    post("/update-product");
    post("/place-order");
    get("/orders");
    post("/update-order");
    get("/{status}-orders");
    */
}
