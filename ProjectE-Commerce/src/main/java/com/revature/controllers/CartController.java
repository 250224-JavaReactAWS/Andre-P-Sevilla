package com.revature.controllers;

import com.revature.dtos.response.ErrorMessage;
import com.revature.models.*;
import com.revature.services.BuyoutService;
import io.javalin.http.Context;

public class CartController {

    private final BuyoutService buyoutService;

    public CartController(BuyoutService buyoutService){
        this.buyoutService = buyoutService;
    }

    public void getAllItems(Context ctx){

        if (ctx.sessionAttribute("userid") == null){
            ctx.status(401);
            ctx.json(new ErrorMessage("Please login in to be able to see your cart."));
            return;
        }

        if (buyoutService.getAllItems(ctx.sessionAttribute("userid")).isEmpty()){
            ctx.status(200);
            ctx.json("Seems like your cart is empty, try adding some items.");
            return;
        }

        ctx.status(200);
        ctx.json(buyoutService.getAllItems(ctx.sessionAttribute("userid")));
    }

    public void addItemToCart(Context ctx){

        if (ctx.sessionAttribute("userid") == null){
            ctx.status(401);
            ctx.json(new ErrorMessage("Please login in to continue adding items to your cart."));
            return;
        }

        //Todo check if the item is already in the cart, if it is, just update the quantity of the item
        ctx.status(200);
        ctx.json(buyoutService.addCartItem(ctx.sessionAttribute("userid"), Integer.parseInt(ctx.pathParam("productId")), Integer.parseInt(ctx.pathParam("quantity"))));

    }

    public void removeItemFromCart(Context ctx){

        if (ctx.sessionAttribute("userid") == null){
            ctx.status(401);
            ctx.json(new ErrorMessage("Please login in to delete items from your cart."));
            return;
        }

        ctx.status(200);
        ctx.json(buyoutService.removeCartItem(ctx.sessionAttribute("userid"), Integer.parseInt(ctx.pathParam("productId"))));

    }

    public void updateItemFromCart(Context ctx){

        if (ctx.sessionAttribute("userid") == null){
            ctx.status(401);
            ctx.json(new ErrorMessage("Please login in to continue updating items from your cart."));
            return;
        }

        ctx.status(200);
        ctx.json(buyoutService.updateCartItem(ctx.sessionAttribute("userid"), Integer.parseInt(ctx.pathParam("productId")), Integer.parseInt(ctx.pathParam("quantity"))));

    }

    public void PlaceOrder(Context ctx){

        if (ctx.sessionAttribute("userid") == null){
            ctx.status(401);
            ctx.json(new ErrorMessage("Please login in to continue."));
            return;
        }

        ctx.status(200);
        buyoutService.buyoutCart(ctx.sessionAttribute("userid"));
        ctx.json("Thank you for buying with us.");
    }

    public void getOrders(Context ctx){

        if (ctx.sessionAttribute("role") != Role.ADMIN){
            ctx.status(403);
            ctx.json(new ErrorMessage("You are not allowed to this page."));
            return;
        }

        ctx.status(200);
        ctx.json(buyoutService.viewAllOrders());
    }

    public void updateOrder(Context ctx){

        if (ctx.sessionAttribute("role") != Role.ADMIN){
            ctx.status(403);
            ctx.json(new ErrorMessage("You are not allowed to this page."));
            return;
        }

        Order order = ctx.bodyAsClass(Order.class);

        ctx.status(200);
        ctx.json(buyoutService.updateOrderStatus(order));

    }

    public void getStatusOrders(Context ctx){

        if (ctx.sessionAttribute("role") != Role.ADMIN){
            ctx.status(403);
            ctx.json(new ErrorMessage("You are not allowed to this page."));
            return;
        }
        ctx.status(200);
        ctx.json(buyoutService.viewOrdersByStatus(Status.valueOf(ctx.pathParam("status"))));
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
