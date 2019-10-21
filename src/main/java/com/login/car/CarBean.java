/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.login.car;

import com.login.util.DataConnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@SessionScoped
public class CarBean extends Car {
    
    /**
     * Creates a new instance of CarBean
     */
    public CarBean() {
    }

    public List<Car> getCars() throws SQLException {

        Connection connect = null;

        try {
            connect = DataConnect.getConnection();

            List<Car> cars = new ArrayList<>();
            PreparedStatement pstmt = connect
                    .prepareStatement("select car_id, cname, color, speed, Manufactured_Country from Car");
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {

                Car car = new Car();
                car.setCid(rs.getInt("car_id"));
                car.setCname(rs.getString("cname"));
                car.setColor(rs.getString("color"));
                car.setSpeed(rs.getInt("speed"));
                car.setMfdctry(rs.getString("Manufactured_Country"));

                cars.add(car);

            }

            // close resources
            rs.close();
            pstmt.close();
            connect.close();

            return cars;
        } catch (SQLException e) {
            return null;
        } finally {
            DataConnect.close(connect);
        }

    }
    
     public String insertCar() {
        int valid = CarDAO.insert(cname, color, speed,mfdctry);
        if (valid != 0) {
            return "cars?faces-redirect=true";
        } else {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "Something went wrong!",
                            "Couldn't insert car."));
            return "cars?faces-redirect=true";
        }

    }
     
     public String deleteSelectedCars() {
         FacesContext facesContext = FacesContext.getCurrentInstance();
         CarPool carPool
            = (CarPool) facesContext.getApplication()
                    .createValueBinding("#{carPool}").getValue(facesContext);
        int carLenght = carPool.getCurrentlySelectedCars().size();
        int[] valid = CarDAO.delete(carPool.getCurrentlySelectedCars());
        if (valid.length == carLenght) {
            return "cars?faces-redirect=true";
        } else {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "Something went wrong!",
                            "Couldn't insert car."));
            return "cars?faces-redirect=true";
        }

    }

}
