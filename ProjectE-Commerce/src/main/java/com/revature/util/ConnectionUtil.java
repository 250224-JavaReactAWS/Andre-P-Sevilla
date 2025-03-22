package com.revature.util;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionUtil {

    //Establish a connection to a database
    private static Connection conn = null;

    //This makes sure that nobody can make an instance of this class
    private ConnectionUtil(){}

    //get instance of a connection
    public static Connection getConnection(){
        //if no connection create one, if there is a connection return that connection.
        try {
            if (conn != null && !conn.isClosed()){
                return conn;
            }
        } catch (SQLException e) {
            // This was an auto generated block
            e.printStackTrace();
            return null;
        }

        //Login information and address
        String url;
        String username;
        String password;

        Properties props = new Properties();

        try {
            props.load(new FileReader("src/main/resources/application.properties"));

            url = props.getProperty("url");
            username = props.getProperty("username");
            password = props.getProperty("password");

            conn = DriverManager.getConnection(url, username, password);
            System.out.println("Connection established to database");

        } catch (IOException | SQLException e) {
            e.printStackTrace();
            System.out.println("could not establish connection");
        }


        return conn;
    }


}


