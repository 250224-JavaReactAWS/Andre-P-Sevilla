package com.revature.repos;

import com.revature.models.Product;
import com.revature.util.ConnectionUtil;

import javax.xml.transform.Result;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAOImpl implements ProductDAO {
    @Override
    public List<Product> getAllProducts() {

        try (Connection conn = ConnectionUtil.getConnection();){
            List<Product> allProducts = new ArrayList<>();

            String sql = "SELECT * from products";

            PreparedStatement ps = conn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery(sql);

            while (rs.next()){
                Product p = new Product();
                p.setProductId(rs.getInt("product_id"));
                p.setName(rs.getString("name"));
                p.setDescription(rs.getString("description"));
                p.setPrice(rs.getInt("price"));
                p.setStock(rs.getInt("stock"));

                allProducts.add(p);
            }

            return allProducts;

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

}

    @Override
    public Product updateProductDetail(Product product) {
        try (Connection conn = ConnectionUtil.getConnection()){

            String sql = "UPDATE products SET description = ? WHERE product_id = ?;";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, product.getDescription());
            ps.setInt(2, product.getProductId());

            int rows = ps.executeUpdate();

            if (rows > 0){
                return product;
            }else return null;


        } catch (SQLException e) {
            System.out.println("Unable to update product description.");
        }

        return null;
    }

    @Override
    public Product createProduct(Product product) {
            try (Connection conn = ConnectionUtil.getConnection()){
                String sql = "INSERT INTO PRODUCTS (name, description, price, stock) VALUES"+ "(?,?,?,?) RETURNING *;";

                PreparedStatement ps = conn.prepareStatement(sql);

                ps.setString(1, product.getName());
                ps.setString(2, product.getDescription());
                ps.setFloat(3, product.getPrice());
                ps.setInt(4,product.getStock());

                ResultSet rs = ps.executeQuery();

                if(rs.next()){
                    Product p = new Product();
                    p.setProductId(rs.getInt("product_id"));
                    p.setName(rs.getString("name"));
                    p.setDescription(rs.getString("description"));
                    p.setPrice(rs.getInt("price"));
                    p.setStock(rs.getInt("stock"));

                    return p;
                }

            } catch (SQLException e) {
                System.out.println("Could not create product.");
                e.printStackTrace();
            }


        return null;
    }

    @Override
    public Product updateProduct(Product product) {
        try (Connection conn = ConnectionUtil.getConnection()){

           String sql = "UPDATE products SET name = ?, description = ?, price = ?, stock = ? WHERE product_id = ?;";

           PreparedStatement ps = conn.prepareStatement(sql);

           ps.setString(1,product.getName());
           ps.setString(2,product.getDescription());
           ps.setFloat(3, product.getPrice());
           ps.setInt(4,product.getStock());

           int rows = ps.executeUpdate();

           if (rows > 0){
               return product;
           }else return null;

        } catch (SQLException e) {
            System.out.println("Unable to update product.");
        }
        return null;
    }

    @Override
    public Product deleteProduct(Product product) {
        try(Connection conn = ConnectionUtil.getConnection()){

            String sql = "DELETE FROM products WHERE product_id = ?;";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, product.getProductId());

            String name = product.getName();
            int id = product.getProductId();
            int rows = ps.executeUpdate();

            if (rows > 0){
                System.out.println("Deleted product: " + name + "with id:" + id);
            } else{
                return null;
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return null;
    }
}
