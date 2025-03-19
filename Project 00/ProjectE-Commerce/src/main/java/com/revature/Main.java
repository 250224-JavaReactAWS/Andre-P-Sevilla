package com.revature;

import com.revature.util.ConnectionUtil;

import java.sql.Connection;

public class Main {
    public static void main(String[] args) {


        System.out.println("Hello, World!");
        Connection conn = ConnectionUtil.getConnection();
    }
}