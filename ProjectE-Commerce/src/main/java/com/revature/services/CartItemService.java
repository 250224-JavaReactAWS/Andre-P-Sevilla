package com.revature.services;

import com.revature.models.CartItem;
import com.revature.models.Product;
import com.revature.models.User;
import com.revature.repos.CartItemDAO;
import com.revature.repos.ProductDAO;
import com.revature.repos.ProductDAOImpl;
import com.revature.repos.UserDAO;


public class CartItemService {

    private CartItemDAO cartitemDAO;

    public CartItemService(CartItemDAO cartitemDAO) {
        this.cartitemDAO = cartitemDAO;
    }

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



}
