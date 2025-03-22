package com.revature.repos;

import com.revature.models.CartItem;
import com.revature.models.Product;
import com.revature.models.User;

import java.util.List;

public interface CartItemDAO {
    /*
     * CRUD Methods
     * User see all products in my cart
     * User add product
     * User Remove product
     * User update quantity
     * Delete Cart for purchasing process. functionality for both cleaning all the
     *                                     cart items and/or starting the purchase process.
     * */

    //List<CartItem> getAllItems(User user); TODO check with Bryan about this

    CartItem addCartProduct(int userId, int productId, int quantity);

    boolean removeCartProduct(int userId, int productId);

    CartItem updateCartProduct(int userId, int productId, int quantity);

    boolean deleteCart(int userId);

    List<CartItem> getAllItems(int userId);
}

