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
    @ManyToOne
    private MotherBoard motherBoard;
    @ManyToOne
    private Memory memory;
    @ManyToOne
    private Storage storage;
    @ManyToOne
    private VideoCard videoCard;
    @ManyToOne
    private PcCase pcCase;
    @ManyToOne
    private PowerSupply powerSupply;
    @ManyToOne
    private OpticalDrive opticalDrive;
    @ManyToOne
    private OperatingSystem operatingSystem;
    @ManyToOne
    private Software software;
    @ManyToOne
    private PcMonitor pcMonitor;
    @ManyToOne
    private ExternalStorage externalStorage;

    private double price;
    private double processingSpeed;
    private double pcStorage;




    public Rig(){}

    public Rig(double price, double processingSpeed, double pcStorage) {
        this();
        this.price = price;
        this.processingSpeed = processingSpeed;
        this.pcStorage = pcStorage;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPcStorage() {
        return pcStorage;
    }

    public void setPcStorage(double pcStorage) {
        this.pcStorage = pcStorage;
    }

    public double getProcessingSpeed() {
        return processingSpeed;
    }

    public void setProcessingSpeed(double processingSpeed) {
        this.processingSpeed = processingSpeed;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public MotherBoard getMotherBoard() {
        return motherBoard;
    }

    public void setMotherBoard(MotherBoard motherBoard) {
        this.motherBoard = motherBoard;
    }

    public Memory getMemory() {
        return memory;
    }

    public void setMemory(Memory memory) {
        this.memory = memory;
    }

    public Storage getStorage() {
        return storage;
    }

    public void setStorage(Storage storage) {
        this.storage = storage;
    }

    public VideoCard getVideoCard() {
        return videoCard;
    }

    public void setVideoCard(VideoCard videoCard) {
        this.videoCard = videoCard;
    }

    public PcCase getPcCase() {
        return pcCase;
    }

    public void setPcCase(PcCase pcCase) {
        this.pcCase = pcCase;
    }

    public PowerSupply getPowerSupply() {
        return powerSupply;
    }

    public void setPowerSupply(PowerSupply powerSupply) {
        this.powerSupply = powerSupply;
    }

    public OpticalDrive getOpticalDrive() {
        return opticalDrive;
    }

    public void setOpticalDrive(OpticalDrive opticalDrive) {
        this.opticalDrive = opticalDrive;
    }

    public OperatingSystem getOperatingSystem() {
        return operatingSystem;
    }

    public void setOperatingSystem(OperatingSystem operatingSystem) {
        this.operatingSystem = operatingSystem;
    }

    public Software getSoftware() {
        return software;
    }

    public void setSoftware(Software software) {
        this.software = software;
    }

    public PcMonitor getPcMonitor() {
        return pcMonitor;
    }

    public void setPcMonitor(PcMonitor pcMonitor) {
        this.pcMonitor = pcMonitor;
    }

    public ExternalStorage getExternalStorage() {
        return externalStorage;
    }

    public void setExternalStorage(ExternalStorage externalStorage) {
        this.externalStorage = externalStorage;
    }

}
