package com.revature.repos;

import com.revature.models.OrderItem;

public interface OrderItemDAO {
    /*
    * Create OrderItemDAO
    * Delete OrderItemDAO
    * */

    OrderItem createOrderItem();

    boolean deleteOrderItem();
}
