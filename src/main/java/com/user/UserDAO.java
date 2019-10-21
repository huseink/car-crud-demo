/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.user;

import com.util.DataConnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class UserDAO {

    public static String validate(String user, String password) {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = DataConnect.getConnection();
            ps = con.prepareStatement("Select fullname, uname, password from Users where uname = ? and password = ?");
            ps.setString(1, user);
            ps.setString(2, password);


            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                
                //result found, means valid inputs
                return rs.getString("fullname");
            }
        } catch (SQLException ex) {
            System.out.println("Login error -->" + ex.getMessage());
            return null;
        } finally {
            DataConnect.close(con);
        }
        return null;
    }
    
    public static int create(String name, String user, String password) {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = DataConnect.getConnection();
            System.out.println(name);
            ps = con.prepareStatement("INSERT INTO Users (fullname,uname,password) VALUES(?,?,?)");
            ps.setString(1, name);
            ps.setString(2, user);
            ps.setString(3, password);

            return ps.executeUpdate();
            
        } catch (SQLException ex) {
            System.out.println("Error -->" + ex.getMessage());
            return 0;
        } finally {
            DataConnect.close(con);
        }
      
    }

}
