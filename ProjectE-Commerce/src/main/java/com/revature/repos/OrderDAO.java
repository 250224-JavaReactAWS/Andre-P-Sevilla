package com.revature.repos;

import com.revature.models.CartItem;
import com.revature.models.Order;

import java.util.List;

public interface OrderDAO {
    /*
    * CRUD Methods
    * Create Order (Buy products)
    * Admin Update status
    * Admin View all orders
    * Admin View by Status
    *
    * */

    Order createOrder(CartItem item);

    Order updateStatus(Order order);

    List<Order> viewByStatus(Order oder);

    List<Order> viewAll(Order order);
}
