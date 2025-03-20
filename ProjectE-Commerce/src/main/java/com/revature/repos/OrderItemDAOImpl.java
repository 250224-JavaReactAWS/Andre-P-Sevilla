package com.revature.repos;

import com.revature.models.CartItem;
import com.revature.models.Order;
import com.revature.models.OrderItem;
import com.revature.util.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderItemDAOImpl implements OrderItemDAO{
    @Override
    public OrderItem createOrderItem(CartItem item, Order order) {
        try (Connection conn = ConnectionUtil.getConnection()) {

            String sql = "SELECT price FROM products WHERE product_id = ?;";

            PreparedStatement ps = conn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            int price = 0;
            if (rs.next()) {
                price = rs.getInt("price");
            }


            sql = "INSERT INTO orderitems (order_id, product_id, quantity, price) Values+" +
                    "(?,?,?,?) Return *;";

            ps = conn.prepareStatement(sql);

            ps.setInt(1, order.getOrderId());
            ps.setInt(2, item.getProductId());
            ps.setInt(3, item.getQuantity());
            ps.setInt(4, price);

            rs = ps.executeQuery();

            if (rs.next()) {
                OrderItem orderItem = new OrderItem(
                        rs.getInt("order_item_id"),
                        rs.getInt("order_id"),
                        rs.getInt("product_id"),
                        rs.getInt("quantity"),
                        rs.getInt("price")
                );

            }


        } catch (SQLException e) {
            System.out.println("Could not create OrderItem");
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public boolean deleteOrderItem() {
        return false;
    }
}
