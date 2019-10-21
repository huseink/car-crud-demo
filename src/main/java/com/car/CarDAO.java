/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.car;

import com.util.DataConnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.user.UserBean;
import java.sql.Statement;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@SessionScoped
public class CarDAO {

    public static int insert(String name, String color, int speed, String country) {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = DataConnect.getConnection();
            ps = con.prepareStatement("INSERT INTO `Car` (`cname`, `color`, `speed`, `Manufactured_Country`) VALUES (?,?,?,?)");
            ps.setString(1, name);
            ps.setString(2, color);
            ps.setInt(3, speed);
            ps.setString(4, country);

            return ps.executeUpdate();
        } catch (SQLException ex) {
            return 0;
        } finally {
            DataConnect.close(con);
        }

    }
    
    public static int[] delete(List<Car> currentlySelectedCars) {
        Connection con = null;
        Statement ps = null;
        
        try { 
            con = DataConnect.getConnection();
            ps = con.createStatement();
            for(Car car:currentlySelectedCars){
              
                ps.addBatch("DELETE FROM `Car` WHERE `Car`.`car_id` = " + car.cid);
                System.out.println(car.cid);
            }
            
   
            return ps.executeBatch();
        } catch (SQLException ex) {
            return new int[0];
        } finally {
            DataConnect.close(con);
        }

    }

}
