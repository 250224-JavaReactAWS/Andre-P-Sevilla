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

    public Product createProduct(Product product){
        return productDAO.createProduct(product);
    }

    public Product updateProduct(Product product){
        return  productDAO.updateProduct(product);
        /*if (product == productDAO.getProductByName(product.getName())){
            return productDAO.updateProduct(product);
        }else {
            return null;
        }*/
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
