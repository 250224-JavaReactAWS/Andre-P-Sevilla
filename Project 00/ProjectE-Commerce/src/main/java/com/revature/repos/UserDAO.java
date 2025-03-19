package com.revature.repos;

import com.revature.models.User;

import java.util.List;

public interface UserDAO{

    /*
    * USER CRUD
    * Create User
    * Update Information: Password, email and name
    * View or read the order history for past purchases
    * Log In
    * */

    User createUser(User user);

    User updateUser(User user);

    User getUserByEmail(String username);



}
