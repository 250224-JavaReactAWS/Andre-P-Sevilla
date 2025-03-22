package com.revature.util;

import com.revature.controllers.CartController;
import com.revature.controllers.ProductController;
import com.revature.controllers.UserController;
import com.revature.models.Order;
import com.revature.models.User;
import com.revature.repos.*;
import com.revature.services.BuyoutService;
import com.revature.services.ProductService;
import com.revature.services.UserService;
import io.javalin.Javalin;

import static io.javalin.apibuilder.ApiBuilder.*;

public class JavalinUtil {

    public static Javalin create(int port){

        UserDAO userDAO = new UserDAOImpl();
        ProductDAO productDAO = new ProductDAOImpl();
        CartItemDAO cartItemDAO = new CartItemDAOImpl();
        OrderDAO orderDAO = new OrderDAOImpl();
        OrderItemDAO orderItemDAO = new OrderItemDAOImpl();

        UserService userService = new UserService(userDAO, orderDAO);
        ProductService productService = new ProductService(productDAO);
        BuyoutService buyoutService = new BuyoutService(cartItemDAO, productDAO, orderDAO, orderItemDAO);

        UserController userController = new UserController(userService);
        ProductController productController = new ProductController(productService);
        CartController cartController = new CartController(buyoutService);

        return Javalin.create(config ->{

            config.router.apiBuilder(() ->{
                path("/users", () -> {
                    post("/register", userController::registerUserHandler);
                    post("/login", userController::loginHandler);
                    post("/update", userController::updateHandler);
                    get("/order-history", userController::viewHistoryHandler);
                });
                path("/products", () ->{
                    get(productController::productsHandler);
                    get("/{product}", productController::detailProductHandler);
                    post("/add-product", productController::addProductHandler);
                    post("/update-product", productController::updateProductHandler);
                    post("/delete-product", productController::deleteProductHandler);
                });
                path("/cart", () -> {
                    get(cartController::getAllItems);
                    post("/add-product+{productId}+{quantity}", cartController::addItemToCart);
                    post("/remove-product+{productId}", cartController::removeItemFromCart);
                    post("/update-product+{productId}+{quantity}", cartController::updateItemFromCart);
                    post("/place-order", cartController::PlaceOrder);
                    get("/orders", cartController::getOrders);
                    post("/update-order", cartController::updateOrder);
                    get("/{status}-orders", cartController::getStatusOrders);
                });
            });
                })
                //.post("/users/register", userController::registerUserHandler)


                .start(port);
    }






    /*//Run Javalin for the Java Server (Creates the option for HTTP requests) NOTE: Requires logger
    * //It's listening to port 7070 meaning that the path for the API will start with http://localhost:7070/
    * var app = Javalin.create(/*config/)
    *        //.get allow us to send a get request to the server (localhost:7070)
    *         .get("/", ctx -> ctx.result("Hello World"))

            .post("/login", ctx -> {

                User requestUser = ctx.bodyAsClass(User.class);

                User loggedIn = null;
                for(User u: ){

                }


            })


            .start(7070);


    /*
     * Path variable with /{var}
     * .get("/test/{x}", ctx -> { int x = Integer.parseInt(ctx.pathParam("x"))
     *
     *       if (num % 2 == 0){
     *           ctx.result("The number" + x + "is even!");
     *       } else {
     *           ctx.result("The number" + x + "is odd!");
     *       }
     * })
     * */
}
