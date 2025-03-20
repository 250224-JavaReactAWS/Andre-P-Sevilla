package com.revature.repos;

import com.revature.models.CartItem;
import com.revature.models.Order;
import com.revature.models.OrderItem;

public interface OrderItemDAO {
    /*
    * Create OrderItemDAO
    * Delete OrderItemDAO
    * */

    OrderItem createOrderItem(CartItem item, Order order);

    boolean deleteOrderItem();
}
