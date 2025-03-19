package com.revature.repos;

import com.revature.models.CartItem;
import com.revature.models.Order;
import com.revature.models.Product;
import com.revature.util.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class OrderDAOImpl implements OrderDAO {
    @Override
    public Order createOrder(CartItem item) {
        try (Connection conn = ConnectionUtil.getConnection()){

            String sql = "INSERT INTO orders (user_id, total_price, status) VALUES"+ "(?,?,?) RETURNING *;";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, item.getUserId());
            //ps.setFloat(2, );

            ResultSet rs = ps.executeQuery();

            if(rs.next()){

            }

        } catch (SQLException e) {
            System.out.println("Could not create product.");
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Order updateStatus(Order order) {
        return null;
    }

    @Override
    public List<Order> viewByStatus(Order order) {
        return List.of();
    }

    @Override
    public List<Order> viewAll(Order order) {
        return List.of();
    }
}
