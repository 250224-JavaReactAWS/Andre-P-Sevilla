package com.revature.controllers;

import com.revature.dtos.response.ErrorMessage;
import com.revature.models.User;
import com.revature.services.UserService;
import io.javalin.http.Context;

public class UserController {

    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    //Register new user

    public void registerUserHandler(Context ctx){
        //Method called whenever a user want to register
        User requestUser = ctx.bodyAsClass(User.class);

        if (!userService.validateEmail(requestUser.getEmail())){
            ctx.status(400);
            ctx.json(new ErrorMessage("Email is not valid. Please write a valid email."));
            return;
        }

        if (!userService.validatePassword(requestUser.getPassword())){
            ctx.status(400);
            ctx.json(new ErrorMessage("Password is not valid. It must be 8 characters long and contain an uppercase and lowercase letter"));
            return;
        }

        if (!userService.isEmailAvailable(requestUser.getEmail())){
            ctx.status(400);
            ctx.json(new ErrorMessage("Username is not valid. It must be at least 8 characters"));
            return;
        }

        User registerUser = userService.registerUser(requestUser);

        if (registerUser == null){
            ctx.status(500);
            ctx.json(new ErrorMessage("Something went wrong! try again later"));
            return;
        }

        ctx.status(201);
        ctx.json(registerUser);
    }

    //login method for authentication
    public void loginHandler(Context ctx){
        User requestUser = ctx.bodyAsClass(User.class);

        User returnedUser = userService.loginUser(requestUser.getEmail(), requestUser.getPassword());

        if (returnedUser == null){
            ctx.json(new ErrorMessage("Username or Password Incorrect"));
            ctx.status(400);
            return;
        }

        ctx.status(200);
        ctx.json(returnedUser);

        ctx.sessionAttribute("userid", returnedUser.getUserid());
        ctx.sessionAttribute("role", returnedUser.getRole());
        ctx.sessionAttribute("email", returnedUser.getEmail());

    }

    //update method
    public void updateHandler(Context ctx){
        User requestUser = ctx.bodyAsClass(User.class);


        if (ctx.sessionAttribute("userid") == null){
            ctx.status(401);
            ctx.json(new ErrorMessage("You must be logged in to update your information"));
            return;
        }

        if (!ctx.sessionAttribute("email").equals(requestUser.getEmail())) {
            ctx.status(403);
            ctx.json(new ErrorMessage("You are not allowed to update that information."));
            return;
        }

        ctx.json(userService.updateUser(requestUser));
    }

    public void viewHistoryHandler(Context ctx){

        if (ctx.sessionAttribute("userid") == null){
            ctx.status(401);
            ctx.json(new ErrorMessage("you must be logged in to view your order history."));
            return;
        }
        ctx.status(200);
        ctx.json(userService.viewOrderHistory(ctx.sessionAttribute("userid")));
    }
    //TODO view order history

}
