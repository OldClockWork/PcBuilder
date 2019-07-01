package com.palta.BuildRig.controller;


import com.palta.BuildRig.Models.*;
import com.palta.BuildRig.data.*;
import com.palta.BuildRig.forms.hardwareType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;


@Controller
@RequestMapping(value = "admin")
public class AdminController {

    @Autowired
    CpuDao cpuDao;
    @Autowired
    CpuCoolerDao cpuCoolerDao;
    @Autowired
    MemoryDao memoryDao;
    @Autowired
    MotherBoardDao motherBoardDao;
    @Autowired
    ExternalStorageDao externalStorageDao;
    @Autowired
    OperatingSystemDao operatingSystemDao;
    @Autowired
    OpticalDriveDao opticalDriveDao;
    @Autowired
    PcCaseDao pcCaseDao;
    @Autowired
    PcMonitorDao pcMonitorDao;
    @Autowired
    PowerSupplyDao powerSupplyDao;
    @Autowired
    SoftwareDao softwareDao;
    @Autowired
    StorageDao storageDao;
    @Autowired
    VideoCardDao videoCardDao;

    @RequestMapping(value = "new-item", method = RequestMethod.GET)
    public String newItem(Model model){

        hardwareType[] hardwareEnums = hardwareType.values();

        model.addAttribute("hardwareType", hardwareEnums);
        model.addAttribute("title","New Hardware");
        return "admin/add-new-hardware";
    }


    @RequestMapping(value = "add-item",  method = RequestMethod.GET)
    public String addItem(Model model, @RequestParam hardwareType hardwareEnums){

        switch (hardwareEnums){
            case CPU:
                model.addAttribute("Component",new Cpu());
                break;
            case CPU_COOLER:
                model.addAttribute("Component",new CpuCooler());
                break;
            case MEMORY:
                model.addAttribute("Component",new Memory());
                break;
            case MOTHERBOARD:
                model.addAttribute("Component",new MotherBoard());
                break;
            case OPERATING_SYSTEM:
                model.addAttribute("Component",new OperatingSystem());
                break;
            case OPTICAL_DRIVE:
                model.addAttribute("Component",new OpticalDrive());
                break;
            case CASE:
                model.addAttribute("Component",new PcCase());
                break;
            case MONITOR:
                model.addAttribute("Component",new PcMonitor());
                break;
            case POWER_SUPPLY:
                model.addAttribute("Component",new PowerSupply());
                break;
            case SOFTWARE:
                model.addAttribute("Component",new Software());
                break;
            case STORAGE:
                model.addAttribute("Component",new Storage());
                break;
            case VIDEO_CARD:
                model.addAttribute("Component",new VideoCard());
                break;
            case EXTERNAL_STORAGE:
                model.addAttribute("Component",new ExternalStorage());
                break;

        }

        return "admin/add-hardware";
    }


    @RequestMapping(value = "add-item",  method = RequestMethod.POST)
    public String addItemProcess(Model model,
                                 @RequestParam hardwareType hardwareEnums,
                                  Cpu cpu,
                                  CpuCooler cpuCooler,
                                  Memory memory,
                                  MotherBoard motherBoard,
                                 ExternalStorage externalStorage,
                                 OperatingSystem operatingSystem,
                                 OpticalDrive opticalDrive,
                                 PcCase pcCase,
                                 PcMonitor pcMonitor,
                                 PowerSupply powerSupply,
                                 Software software,
                                 Storage storage,
                                 VideoCard videoCard){

        switch (hardwareEnums){
            case CPU:
                cpuDao.save(cpu);
                break;
            case CPU_COOLER:
                cpuCoolerDao.save(cpuCooler);
                break;
            case MEMORY:
                memoryDao.save(memory);
                break;
            case MOTHERBOARD:
                motherBoardDao.save(motherBoard);
                break;
            case EXTERNAL_STORAGE:
                externalStorageDao.save(externalStorage);
                break;
            case OPERATING_SYSTEM:
                operatingSystemDao.save(operatingSystem);
                break;
            case OPTICAL_DRIVE:
                opticalDriveDao.save(opticalDrive);
                break;
            case CASE:
                pcCaseDao.save(pcCase);
                break;
            case MONITOR:
                pcMonitorDao.save(pcMonitor);
                break;
            case POWER_SUPPLY:
                powerSupplyDao.save(powerSupply);
                break;
            case SOFTWARE:
                softwareDao.save(software);
                break;
            case STORAGE:
                storageDao.save(storage);
                break;
            case VIDEO_CARD:
                videoCardDao.save(videoCard);
                break;
        }
        return "redirect:/admin/new-item";
    }
}

