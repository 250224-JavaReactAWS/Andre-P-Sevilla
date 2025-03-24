package com.revature.controllers;

import com.revature.dtos.response.ErrorMessage;
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

        if (ctx.pathParam("product") == null){

            ctx.status(401);
            ctx.json(new ErrorMessage("Please add a valid product."));
            return;

        }

        ctx.json(productService.getProductDetail(ctx.pathParam("product")));
    }

    public void addProductHandler (Context ctx){
        Product product = ctx.bodyAsClass(Product.class);

        if (Role.ADMIN != ctx.sessionAttribute("role")){
            ctx.status(403);
            ctx.json(new ErrorMessage("You are not allowed to add a product."));
            return;
        }

        if (product.getPrice() < 0){
            ctx.status(401);
            ctx.json(new ErrorMessage("The price can not be less than 0"));
        }

        if (product.getStock() < 0){
            ctx.status(401);
            ctx.json(new ErrorMessage("The stock can not be less than 0"));
        }

        ctx.status(200);
        ctx.json(productService.createProduct(product));
    }

    public void updateProductHandler(Context ctx){
        Product product = ctx.bodyAsClass(Product.class);

        if (Role.ADMIN != ctx.sessionAttribute("role")){
            ctx.status(403);
            ctx.json(new ErrorMessage("You are not allowed to update this product."));
            return;
        }

        if (product.getPrice() < 0){
            ctx.status(401);
            ctx.json(new ErrorMessage("The price can not be less than 0"));
        }

        if (product.getStock() < 0){
            ctx.status(401);
            ctx.json(new ErrorMessage("The stock can not be less than 0"));
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
        }else {
            ctx.status(200);
            ctx.json("There is no product with that name.");
        }

    }

}
