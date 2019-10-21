/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.car;


public class Car {
    
    protected Integer cid;
    protected String cname;
    protected String color;
    protected Integer speed;
    protected String mfdctry;

    public Car() {
    }

    public Car(Integer cid, String cname, String color, Integer speed, String mfdctry) {
        this.cid = cid;
        this.cname = cname;
        this.color = color;
        this.speed = speed;
        this.mfdctry = mfdctry;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getSpeed() {
        return speed;
    }

    public void setSpeed(Integer speed) {
        this.speed = speed;
    }

    public String getMfdctry() {
        return mfdctry;
    }

    public void setMfdctry(String mfdctry) {
        this.mfdctry = mfdctry;
    }
    
    
    
    
    
}
