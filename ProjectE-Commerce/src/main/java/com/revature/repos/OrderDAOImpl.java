package com.revature.repos;

import com.revature.models.*;
import com.revature.util.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDAOImpl implements OrderDAO {


    @Override
    public Order createOrder(int userId, float totalprice) {
        try (Connection conn = ConnectionUtil.getConnection()){

            String sql = "INSERT INTO orders (user_id, total_price) VALUES"+ "(?,?) RETURNING *;";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, userId);
            ps.setFloat(2, totalprice);

            ResultSet rs = ps.executeQuery();

            if(rs.next()){
                Order order = new Order();
                order.setOrderId(rs.getInt("order_id"));
                order.setUserId(rs.getInt("user_id"));
                order.setTotalPrice(rs.getFloat("total_price"));
                order.setStatus(Status.valueOf(rs.getString("status")));

                return order;
            }

        } catch (SQLException e) {
            System.out.println("Could not create product.");
            e.printStackTrace();
            return null;
        }
        return null;
    }

    @Override
    public Order updateStatus(Order order) {
        try (Connection conn = ConnectionUtil.getConnection()){

            String sql = "UPDATE orders SET status = ? WHERE order_id = ?;";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1,String.valueOf(order.getStatus()));
            ps.setInt(2, order.getOrderId());

            int rs = ps.executeUpdate();

            if (rs > 0){
                return order;
            }


        } catch (SQLException e) {
           e.printStackTrace();
            return null;
        }


        return null;
    }

    @Override
    public List<Order> viewAll() {
        try (Connection conn = ConnectionUtil.getConnection()){
            List<Order> allOrders = new ArrayList<>();

            String sql = "SELECT * FROM orders;";

            PreparedStatement ps = conn.prepareStatement(sql);


            ResultSet rs = ps.executeQuery();

            if (rs.next()){
                Order order = new Order();
                order.setOrderId(rs.getInt("order_id"));
                order.setUserId(rs.getInt("user_id"));
                order.setTotalPrice(rs.getFloat("total_price"));
                order.setStatus(Status.valueOf(rs.getString("status")));

                allOrders.add(order);
            }

            return allOrders;

        } catch (SQLException e) {
            System.out.println("Unable to get list of orders");
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public List<Order> viewByStatus(Status status) {
        try (Connection conn = ConnectionUtil.getConnection()){
            List<Order> allOrders = new ArrayList<>();

            String sql = "SELECT * FROM orders WHERE status = ?;";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, String.valueOf(status));

            ResultSet rs = ps.executeQuery();

            if (rs.next()){
                Order order = new Order();
                order.setOrderId(rs.getInt("order_id"));
                order.setUserId(rs.getInt("user_id"));
                order.setTotalPrice(rs.getFloat("total_price"));
                order.setStatus(Status.valueOf(rs.getString("status")));
                //todo check the timestamp once this works in the database

                allOrders.add(order);
            }

            return allOrders;

        } catch (SQLException e) {
            System.out.println("Unable to get Orders by Status");
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public List<Order> viewByUserId(int userId) {
        try (Connection conn = ConnectionUtil.getConnection()){
            List<Order> allOrders = new ArrayList<>();

            String sql = "SELECT * FROM orders WHERE user_id = ?;";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, userId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                Order order = new Order();
                order.setOrderId(rs.getInt("order_id"));
                order.setUserId(rs.getInt("user_id"));
                order.setTotalPrice(rs.getFloat("total_price"));
                order.setStatus(Status.valueOf(rs.getString("status")));
                //todo check the timestamp once this works in the database

                allOrders.add(order);
            }

            return allOrders;

        } catch (SQLException e) {
            System.out.println("Unable to get Orders by User Id");
            e.printStackTrace();
            return null;
        }

    }
}
