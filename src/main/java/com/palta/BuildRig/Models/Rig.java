package com.palta.BuildRig.Models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Rig {

    @Id
    @GeneratedValue
    private int id;

    @ManyToOne
    private User user;

    @NotNull
    private String name;

    @ManyToOne
    private Cpu cpu;

    @ManyToOne
    private CpuCooler cpuCooler;


//    @NotNull
//    private MotherBoard motherBoard;
//    @NotNull
//    private Memory memory;
//    @NotNull
//    private Storage storage;
//    @NotNull
//    private VideoCard videoCard;
//    @NotNull
//    private PcCase pcCase;
//    @NotNull
//    private PowerSupply powerSupply;
//    @NotNull
//    private OpticalDrive opticalDrive;
//    @NotNull
//    private OperatingSystem operatingSystem;
//    @NotNull
//    private Software software;
//    @NotNull
//    private PcMonitor pcMonitor;
//    @NotNull
//    private ExternalStorage externalStorage;

    private int price;
    private int processingSpeed;




    public Rig(){}

    public Rig(int price, int processingSpeed) {
        this.price = price;
        this.processingSpeed = processingSpeed;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() { return id; }

    public Cpu getCpu() {
        return cpu;
    }

    public void setCpu(Cpu cpu) {
        this.cpu = cpu;
    }

    public CpuCooler getCpuCooler() {
        return cpuCooler;
    }

    public void setCpuCooler(CpuCooler cpuCooler) {
        this.cpuCooler = cpuCooler;
    }

    public int getPrice(int itemPrice) {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getProcessingSpeed() {
        return processingSpeed;
    }

    public void setProcessingSpeed(int processingSpeed) {
        this.processingSpeed = processingSpeed;
    }

    public int getPrice() {
        return price;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
