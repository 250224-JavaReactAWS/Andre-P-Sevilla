package com.revature.controllers;

import com.revature.models.Product;
import com.revature.models.Role;
import com.revature.services.ProductService;
import io.javalin.http.Context;

public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService){
        this.productService = productService;
    }

    public void productsHandler(Context ctx){
        ctx.status(200);
        ctx.json(productService.getAllProducts());
    }

    public void detailProductHandler(Context ctx){
        ctx.status(200);
        ctx.json(productService.getProductDetail(ctx.pathParam("product")));
    }

    public void addProductHandler (Context ctx){
        Product product = ctx.bodyAsClass(Product.class);

        if (Role.ADMIN != ctx.sessionAttribute("role")){
            ctx.status(403);
            ctx.json("You are not allowed to add a product.");
            return;
        }

        ctx.status(200);
        ctx.json(productService.createProduct(product));
    }

    public void updateProductHandler(Context ctx){
        Product product = ctx.bodyAsClass(Product.class);

        if (Role.ADMIN != ctx.sessionAttribute("role")){
            ctx.status(403);
            ctx.json("You are not allowed to update this product.");
            return;
        }

        //System.out.println(product.getName() + " " + product.getDescription() + " " + product.getPrice() + " " +product.getStock());
        ctx.status(200);
        ctx.json(productService.updateProduct(product));
    }

    public void deleteProductHandler(Context ctx){
        Product product = ctx.bodyAsClass(Product.class);

        if (Role.ADMIN != ctx.sessionAttribute("role")){
            ctx.status(403);
            ctx.json("You are not allowed to add a product.");
            return;
        }

        if (productService.deleteProduct(product.getName())){
            ctx.status(200);
            ctx.json("Deleted product: " +product.getName());
        }

    }

}
