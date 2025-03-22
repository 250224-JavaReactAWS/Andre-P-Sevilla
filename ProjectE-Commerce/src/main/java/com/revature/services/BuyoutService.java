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
    public CartItem addCartItem(User user, Product product, int quantity){
        return cartitemDAO.addCartProduct(user, product, quantity);
        /*
        * TODO Check if this is correct or if it needs to be with UserDAO and ProductDAO with the get by function
        */
    }

    public boolean removeCartItem(User user, Product product){
        return cartitemDAO.removeCartProduct(user, product);
    }

    public CartItem updateCartItem(User user, Product product, int quantity){
        return cartitemDAO.updateCartProduct(user, product, quantity);
    }

    public boolean cleanCart(User user){
        return cartitemDAO.deleteCart(user);
    }

    public List<CartItem> getAllItems(User user){
        return cartitemDAO.getAllItems(user);
    }

    public void buyoutCart(User user){
        List<CartItem> listItems = cartitemDAO.getAllItems(user);
        float totalprice = 0;

        //First we get the total price directly from the CartItems, we run the ProductDAO to get the product price and multiply by the quantity in the cart
        for (CartItem x: listItems){
            Product p = productDAO.getProductByID(x.getProductId());
            totalprice += p.getPrice() * x.getQuantity();
        }
        //Once we have the total, creation of the Order
        Order order = orderDAO.createOrder(user, totalprice);

        //With the Order id, we pass all the items from the cart to the orderItems table.
        for (CartItem x: listItems){
            OrderItem oI = orderItemDAO.createOrderItem(x, order);
            //For testing
            System.out.println("Created order item: " + oI.getOrderItemId());
        }

        //We need to clear the cart as the process has ended
        cleanCart(user);
        System.out.println("Thank you for buying with us.");
    }

    // ----------------- Order Service ------------------

    public Order updateOrderStatus(int orderId, Status status){
        Order rOrder = new Order();
        rOrder.setOrderId(orderId);
        rOrder.setStatus(status);

        return orderDAO.updateStatus(rOrder);
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
