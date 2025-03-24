package com.revature.repos;

import com.revature.models.Role;
import com.revature.models.User;
import com.revature.util.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAOImpl implements UserDAO {


    @Override
    public User createUser(User user) {
        try (Connection conn = ConnectionUtil.getConnection()){

            String sql = "INSERT INTO users (first_name, last_name, email, password) VALUES (?,?,?,?) RETURNING *;";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, user.getFirstName());
            ps.setString(2, user.getLastName());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getPassword());

            ResultSet rs = ps.executeQuery();

            if(rs.next()){
                User u = new User();

                u.setUserid(rs.getInt("user_id"));
                u.setFirstName(rs.getString("first_name"));
                u.setLastName(rs.getString("last_name"));
                u.setEmail(rs.getString("email"));
                u.setPassword(rs.getString("password"));
                u.setRole(Role.valueOf(rs.getString("role")));

                return u;
            }

        } catch (SQLException e) {
            System.out.println("could not create user");
            e.printStackTrace();
            return null;
        }

        return null;
    }

    @Override
    public User updateUser(User user) {
        try(Connection conn = ConnectionUtil.getConnection()){

            String sql = "UPDATE users SET first_name = ?, last_name = ?, email = ?, password = ? WHERE email = ?;";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, user.getFirstName());
            ps.setString(2, user.getLastName());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getPassword());
            ps.setString(5, user.getEmail());

            int rows = ps.executeUpdate();

            if (rows > 0 ){
                return user;
            }else return null;

        } catch (SQLException e) {
            System.out.println("Could not update user.");
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public User getUserByEmail(String email) {
        try (Connection conn = ConnectionUtil.getConnection()){

            String sql = "SELECT * FROM users WHERE email = ?";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, email);

            ResultSet rs = ps.executeQuery();

            if (rs.next()){
                User u = new User();

                u.setUserid(rs.getInt("user_id"));
                u.setFirstName(rs.getString("first_name"));
                u.setLastName(rs.getString("last_name"));
                u.setEmail(rs.getString("email"));
                u.setPassword(rs.getString("password"));
                u.setRole(Role.valueOf(rs.getString("role")));

                return u;
            }

        } catch (SQLException e) {
            System.out.println("Could not retrieve user by email");
            e.printStackTrace();
            return null;
        }

        return null;
    }
}
