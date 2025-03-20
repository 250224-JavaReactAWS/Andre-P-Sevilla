package com.revature.repos;

import com.revature.models.Product;

import java.util.List;

public interface ProductDAO {
    /*
    * CRUD methods
    * User and Admin Get all items
    * User and Admin Get details of an item
    * Admin create products
    * Admin Update products details (Name, price, stock)
    * Admin Delete product
    * */

    List<Product> getAllProducts();

    Product getProductByName(String name);

    Product createProduct(Product product);

    Product updateProduct(Product product);

    boolean deleteProductByName(String name);
}
