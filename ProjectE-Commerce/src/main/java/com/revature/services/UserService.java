package com.revature.services;

import com.revature.models.Order;
import com.revature.models.User;
import com.revature.repos.OrderDAO;
import com.revature.repos.UserDAO;

import java.util.List;
import java.util.regex.Pattern;

public class UserService {

    private UserDAO userDAO;
    private OrderDAO orderDAO;

    public UserService(UserDAO userDAO, OrderDAO orderDAO){
        this.userDAO = userDAO;
        this.orderDAO = orderDAO;
    }

    public boolean validateEmail(String email){

        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

        // Compile the regex
        Pattern p = Pattern.compile(emailRegex);

        // Check if email matches the pattern
        return email != null && p.matcher(email).matches();
    }

    public boolean isEmailAvailable(String email){
        return userDAO.getUserByEmail(email) == null;
    }

    public boolean validatePassword(String password){

        boolean correcteLenght = password.length() >= 8;
        boolean hasLowercase = false;
        boolean hasUppercase = false;

        for (char c: password.toCharArray()){
            if(Character.isUpperCase(c)){
                hasUppercase = true;
            } else if (Character.isLowerCase(c)) {
                hasLowercase = true;
            }
        }
        return correcteLenght && hasLowercase && hasUppercase;
    }

    public User registerUser(User user){
        return userDAO.createUser(user);
    }

    public User loginUser(String email, String password){
        User returnedUser = userDAO.getUserByEmail(email);
        if (returnedUser == null){
            return null;
        }else if (returnedUser.getPassword().equals(password)){
            return returnedUser;
        }
        return null;
    }

    public User updateUser(User user){
        //User returnedUser = new User(firstname, lastname, email, password);
        /*
        if (user == userDAO.getUserByEmail(user.getEmail())){
            return userDAO.updateUser(user);
        }else return null;
        */
        return userDAO.updateUser(user);
    }

    public List<Order> viewOrderHistory(int userId){

        return orderDAO.viewByUserId(userId);
    }

}
