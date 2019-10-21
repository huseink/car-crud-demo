/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.login.util;

import java.sql.Connection;
import java.sql.DriverManager;


public class DataConnect {

    public static Connection getConnection() {
        try {
            
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    //TODO : FIX DB CONNECTION
                    "jdbc:mysql://localhost:3306/DB?useUnicode=true&characterEncoding=utf-8", "root", "");
            return con;
        } catch (Exception ex) {
            System.out.println("Database.getConnection() Error -->"
                    + ex.getMessage());
            return null;
        }
    }

    public static void close(Connection con) {
        try {
            con.close();
        } catch (Exception ex) {
        }
    }

}
