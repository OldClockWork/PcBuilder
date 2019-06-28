package com.palta.BuildRig.Models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Memory {

    @Id
    @GeneratedValue
    private int id;

    private String name;
    private int itemPrice;
    private String effectWhat;
    private double pcValue;


    public Memory() {}

    public Memory(String name, int itemPrice, String effectWhat, double pcValue) {
        this();
        this.name = name;
        this.itemPrice = itemPrice;
        this.effectWhat = effectWhat;
        this.pcValue = pcValue;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(int itemPrice) {
        this.itemPrice = itemPrice;
    }

    public String getEffectWhat() {
        return effectWhat;
    }

    public void setEffectWhat(String effectWhat) {
        this.effectWhat = effectWhat;
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
