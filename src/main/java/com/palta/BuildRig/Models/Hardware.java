package com.palta.BuildRig.Models;




import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Hardware {

    @Id
    @GeneratedValue
    private int id;
    private String name;
    private double itemPrice;
    private double pcValue;
    private String img;


    public Hardware() {}

    public Hardware(String name, double itemPrice, double pcValue, String img) {
        this();
        this.name = name;
        this.itemPrice = itemPrice;
        this.pcValue = pcValue;
        this.img = img;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }

    public double getPcValue() {
        return pcValue;
    }

    public void setPcValue(double pcValue) {
        this.pcValue = pcValue;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
