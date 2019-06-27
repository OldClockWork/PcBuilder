package com.palta.BuildRig.forms;

public enum hardwareType {

    CPU("CPU"),
    CPU_COOLER("CPU Cooler"),
    MOTHERBOARD("Motherboard"),
    MEMORY("Memory"),
    STORAGE("Storage"),
    VIDEO_CARD("Video Card"),
    CASE("Case"),
    POWER_SUPPLY("Power Supply"),
    OPTICAL_DRIVE("Optical Drive"),
    OPERATING_SYSTEM("Operating System"),
    SOFTWARE("Software"),
    MONITOR("Monitor"),
    EXTERNAL_STORAGE("External");

    private final String name;

    hardwareType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}