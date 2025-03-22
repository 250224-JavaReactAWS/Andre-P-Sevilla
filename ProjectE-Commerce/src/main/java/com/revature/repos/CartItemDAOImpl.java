package com.revature.repos;

import com.revature.models.CartItem;
import com.revature.models.Product;
import com.revature.models.User;
import com.revature.util.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CartItemDAOImpl implements CartItemDAO {


   /* @Override TODO check with bryan about this
    public List<CartItem> getAllItems(User user) {
        try (Connection conn = ConnectionUtil.getConnection()){
            List<CartItem> allItems = new ArrayList<>();

            String sql = "SELECT cartitems.cart_item_id, cartitems.quantity, products.name from cartitems JOIN products"+
                            "ON cartitems.product_id=products.product_id WHERE user_id = ?;";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, user.getUserid());

            ResultSet rs = ps.executeQuery();

            if (rs.next()){
                CartItem cI = new CartItem();

                cI.setCartItemId();

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }*/

    @Override
    public CartItem addCartProduct(User user, Product product, int quantity) {
        try (Connection conn = ConnectionUtil.getConnection()){

            String sql = "INSERT INTO cartitems (user_id, product_id, quantity) VALUES (?,?,?) RETURNING *;";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, user.getUserid());
            ps.setInt(2, product.getProductId());
            ps.setInt(3, quantity);

            ResultSet rs = ps.executeQuery();

            if(rs.next()){
                CartItem cI = new CartItem( rs.getInt("cart_item_id"),
                                            rs.getInt("user_id"),
                                            rs.getInt("product_id"),
                                            rs.getInt("quantity"));

                return cI;
            }


        } catch (SQLException e) {
            System.out.println("Unable to add product to the cart");
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public boolean removeCartProduct(User user, Product product) {
        try (Connection conn = ConnectionUtil.getConnection()){

            String sql = "DELETE FROM cartitems WHERE user_id = ? AND product_id = ?;";

            PreparedStatement ps = conn.prepareStatement(sql);

            int id = product.getProductId();
            int rows = ps.executeUpdate();

            if (rows > 0){
                System.out.println("Deleted Item number: "+ id);
                return true;
            } else{
                return false;
            }


        } catch (SQLException e) {
            System.out.println("Could not Delete Item");
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public CartItem updateCartProduct(User user, Product product, int quantity) {
        try (Connection conn = ConnectionUtil.getConnection()){

            String sql = "UPDATE cartitems SET quantity = ? WHERE product_id = ? AND user_id = ?;";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, quantity);
            ps.setInt(2,product.getProductId());
            ps.setInt(3,user.getUserid());

            ResultSet rs = ps.executeQuery();

            if (rs.next()){
                CartItem item = new CartItem(
                        rs.getInt("cart_item_id"),
                        rs.getInt("user_id"),
                        rs.getInt("product_id"),
                        rs.getInt("quantity")
                );

                return item;
            }else return null;

        } catch (SQLException e) {
            System.out.println("Unable to update item.");
        }


        return null;
    }

    @Override
    public boolean deleteCart(User user) {
        try (Connection conn = ConnectionUtil.getConnection()){

            String sql = "DELETE FROM cartitems WHERE user_id = ?;";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1,user.getUserid());

            int rows = ps.executeUpdate();

            if (rows > 0){
                System.out.println("Deleted items from cart");
            }else return false;

        } catch (SQLException e) {
            System.out.println("Unable to delete items.");
        }

        return false;
    }

    @Override
    public List<CartItem> getAllItems(User user) {
        try (Connection conn = ConnectionUtil.getConnection()){
            List<CartItem> allItems = new ArrayList<>();

            String sql = "SELECT * FROM cartitems WHERE user_id = ?;";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, user.getUserid());

            ResultSet rs = ps.executeQuery();

            if (rs.next()){
                CartItem item = new CartItem();
                item.setCartItemId(rs.getInt("cart_item_id"));
                item.setProductId(rs.getInt("product_id"));
                item.setUserId(rs.getInt("user_id"));
                item.setQuantity(rs.getInt("quantity"));

                allItems.add(item);
            }

            return allItems;

        } catch (SQLException e) {
            System.out.println("Unable to get list of items");
            e.printStackTrace();
        }

        return null;
    }
}
