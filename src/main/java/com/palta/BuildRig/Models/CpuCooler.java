package com.palta.BuildRig.Models;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
public class CpuCooler {


    @Id
    @GeneratedValue
    private int id;

    private String name;
    private double itemPrice;
    private double pcValue;

    public CpuCooler(){}

    public CpuCooler(String name, double itemPrice, double pcValue) {
        this();
        this.name = name;
        this.itemPrice = itemPrice;
        this.pcValue = pcValue;
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

    public int getId() {
        return id;
    }
}
