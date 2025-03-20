package com.revature.services;

import com.revature.models.Product;
import com.revature.repos.ProductDAO;

import java.util.List;

public class ProductService {

    private ProductDAO productDAO;

    public ProductService(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    //todo create this check
    public boolean doProductExist(){
        return false;
    }

    public Product createProduct(String name, String description, float price, int stock){

        Product returnProduct = new Product(name, description, price, stock);

        return productDAO.createProduct(returnProduct);
    }

    public Product updateProduct(String name, String description, float price, int stock){
        Product returnProduct = new Product(name, description, price, stock);

        if (returnProduct == productDAO.getProductByName(name)){
            return productDAO.updateProduct(returnProduct);
        }else {
            return null;
        }
    }

    public Product getProductDetail(String name){
            return productDAO.getProductByName(name);
    }

    public List<Product> getAllProducts(){
        return productDAO.getAllProducts();
    }

    public boolean deleteProduct(String name){
        return productDAO.deleteProductByName(name);
    }

}
