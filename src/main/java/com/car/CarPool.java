/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.car;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;


@ManagedBean
@SessionScoped
public class CarPool implements Serializable {

    FacesContext facesContext = FacesContext.getCurrentInstance();
    CarBean carBean
            = (CarBean) facesContext.getApplication()
                    .createValueBinding("#{carBean}").getValue(facesContext);

    private static final long serialVersionUID = 1L;

    private List<Car> currentlySelectedCars = new ArrayList<>();

    public CarPool() {
        try {
            this.carPool = carBean.getCars();
        } catch (SQLException ex) {
            Logger.getLogger(CarPool.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }

    public List<Car> getCurrentlySelectedCars() {
        return currentlySelectedCars;
    }

    
    
    private List<Car> carPool;
 
  public List<Car> getCarPool() {
        return carPool;
    }

    public void setCurrentlySelectedCars(List<Car> currentlySelectedCars) {
        this.currentlySelectedCars = currentlySelectedCars;
    }

    public void onSelect(Car car, String typeOfSelection, String indexes) {
        System.out.println("OnSelect:" + car + " typeOfSelection: " + typeOfSelection + " indexes: " + indexes);
        if (null != car) {
            getCurrentlySelectedCars().add(car);
        } else if (null != indexes) {
            String[] indexArray = indexes.split(",");
            for (String index : indexArray) {
                int i = Integer.valueOf(index);
                Car newCar = carPool.get(i);
                if (!currentlySelectedCars.contains(newCar)) {
                    getCurrentlySelectedCars().add(newCar);
                }
            }
        }
    }

    public void onDeselect(Car car, String typeOfSelection, String indexes) {
        System.out.println("OnDeselect:" + car + " typeOfSelection: " + typeOfSelection + " indexes: " + indexes);
        if (null != car) {
            getCurrentlySelectedCars().remove(car);
        } else if (null != indexes) {
            String[] indexArray = indexes.split(",");
            for (String index : indexArray) {
                int i = Integer.valueOf(index);
                getCurrentlySelectedCars().remove(carPool.get(i));
            }
        }
    }
}
