package com.revature.services;

import com.revature.models.*;
import com.revature.repos.*;

import java.util.List;


public class BuyoutService {

    private CartItemDAO cartitemDAO;
    private ProductDAO productDAO;
    private OrderDAO orderDAO;
    private OrderItemDAO orderItemDAO;

    public BuyoutService(CartItemDAO cartitemDAO, ProductDAO productDAO, OrderDAO orderDAO, OrderItemDAO orderItemDAO) {
        this.cartitemDAO = cartitemDAO;
        this.productDAO = productDAO;
        this.orderDAO = orderDAO;
        this.orderItemDAO = orderItemDAO;
    }

    //-------------------- Cart Item Service -----------------
    public CartItem addCartItem(int userId, int productId, int quantity){
        return cartitemDAO.addCartProduct(userId, productId, quantity);
        /*
        * TODO Check if this is correct or if it needs to be with UserDAO and ProductDAO with the get by function
        */
    }

    public boolean removeCartItem(int userId, int productId){
        return cartitemDAO.removeCartProduct(userId, productId);
    }

    public CartItem updateCartItem(int userId, int productId, int quantity){
        return cartitemDAO.updateCartProduct(userId, productId, quantity);
    }

    public boolean cleanCart(int userId){
        return cartitemDAO.deleteCart(userId);
    }

    public List<CartItem> getAllItems(int userId){
        return cartitemDAO.getAllItems(userId);
    }

    public boolean buyoutCart(int userId){
        List<CartItem> listItems = cartitemDAO.getAllItems(userId);
        float totalprice = 0;

        try {//First we get the total price directly from the CartItems, we run the ProductDAO to get the product price and multiply by the quantity in the cart
            for (CartItem x : listItems) {
                Product p = productDAO.getProductByID(x.getProductId());
                totalprice += p.getPrice() * x.getQuantity();
            }
            //Once we have the total, creation of the Order
            Order order = orderDAO.createOrder(userId, totalprice);

            //With the Order id, we pass all the items from the cart to the orderItems table.
            for (CartItem x : listItems) {
                OrderItem oI = orderItemDAO.createOrderItem(x, order);
                //For testing
                System.out.println("Created order item: " + oI.getOrderItemId());
            }
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        //We need to clear the cart as the process has ended
        cleanCart(userId);
        System.out.println("Thank you for buying with us.");
        return true;
    }

    // ----------------- Order Service ------------------

    public Order updateOrderStatus(Order order){

        return orderDAO.updateStatus(order);
    }

    public List<Order> viewAllOrders(){
        return orderDAO.viewAll();
    }

    public List<Order> viewOrdersByStatus(Status status){
        return orderDAO.viewByStatus(status);
    }


    // -------------- Order Item Service -----------------
    public boolean deleteOrderItem(){
        return orderItemDAO.deleteOrderItem();
    }

}
