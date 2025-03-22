package com.revature.repos;

import com.revature.models.CartItem;
import com.revature.models.Order;
import com.revature.models.Status;
import com.revature.models.User;

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

    Order createOrder(int userId, float totalprice);

    Order updateStatus(Order order);

    List<Order> viewByStatus(Status status);

    List<Order> viewAll();

    List<Order> viewByUserId(int userId);
}
