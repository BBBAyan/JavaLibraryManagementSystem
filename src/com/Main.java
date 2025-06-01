package com;

import com.login.LoginService;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        System.out.println("----------------Welcome to Library-------------------");
        LoginService loginService = new LoginService();
        loginService.doLogin();

        System.out.println("Program finished");
        System.out.println("----------------Library Management System-------------------");
    }
}