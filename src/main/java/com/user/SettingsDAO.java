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
import com.user.UserBean;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
/**
 *
 * @author alperk
 */
@ManagedBean
@SessionScoped
public class SettingsDAO {

    public static int update(String name, String user, String password) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        UserBean userBean = (UserBean)facesContext.getApplication().createValueBinding("#{userBean}").getValue(facesContext);
        
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = DataConnect.getConnection();
            ps = con.prepareStatement("UPDATE Users SET fullname = ?, uname = ?, password = ? WHERE Users.uname = ?");
            ps.setString(1, name);
            ps.setString(2, user);
            ps.setString(3, password);
            ps.setString(4, userBean.username);
            
            return ps.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("Login error -->" + ex.getMessage());
            System.out.println(userBean.username);
            return 0;
        } finally {
            DataConnect.close(con);
        }

    }

}
