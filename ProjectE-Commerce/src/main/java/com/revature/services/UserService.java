package com.revature.services;

import com.revature.models.User;
import com.revature.repos.UserDAO;

import java.util.List;

public class UserService {

    private UserDAO userDAO;

    public UserService(UserDAO userDAO){
        this.userDAO = userDAO;
    }

    public boolean validateEmail(String email){

        return email.length() >= 8;
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

    public User registerUser(String firstname, String lastname, String email, String password){
        User userToBeSaved = new User(firstname, lastname, email, password);

        return userDAO.createUser(userToBeSaved);
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

    public User updateUser(String firstname, String lastname, String email, String password){
        User returnedUser = new User(firstname, lastname, email, password);
        if ( returnedUser == userDAO.getUserByEmail(email)){
            return userDAO.updateUser(returnedUser);
        }else return null;
    }

}
