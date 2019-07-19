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

import javax.servlet.http.HttpSession;


@Controller
@RequestMapping(value = "hardware")
public class HardwareController {

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
    public String newItem(Model model, HttpSession session){

        if (session.getAttribute("currentUser") == null ||
            !session.getAttribute("currentUser").equals("micih1@hotmail.com")) {

            return "redirect:/buildRig";
        }

        hardwareType[] hardwareEnums = hardwareType.values();

        model.addAttribute("hardwareType", hardwareEnums);
        model.addAttribute("title","New Hardware");
        return "hardware/add-new-hardware";
    }


    @RequestMapping(value = "add-item",  method = RequestMethod.GET)
    public String addItem(Model model, @RequestParam hardwareType hardwareEnums, HttpSession session){

        if (session.getAttribute("currentUser") == null ||
                !session.getAttribute("currentUser").equals("micih1@hotmail.com")) {

            return "redirect:/buildRig";
        }

        switch (hardwareEnums){
            case CPU:
                model.addAttribute("Component",new Cpu());
                model.addAttribute("value","GHz");
                break;
            case CPU_COOLER:
                model.addAttribute("Component",new CpuCooler());
                model.addAttribute("value","none");
                break;
            case MEMORY:
                model.addAttribute("Component",new Memory());
                model.addAttribute("value","none");
                break;
            case MOTHERBOARD:
                model.addAttribute("Component",new MotherBoard());
                model.addAttribute("value","none");
                break;
            case OPERATING_SYSTEM:
                model.addAttribute("Component",new OperatingSystem());
                model.addAttribute("value","none");
                break;
            case OPTICAL_DRIVE:
                model.addAttribute("Component",new OpticalDrive());
                model.addAttribute("value","none");
                break;
            case CASE:
                model.addAttribute("Component",new PcCase());
                model.addAttribute("value","none");
                break;
            case MONITOR:
                model.addAttribute("Component",new PcMonitor());
                model.addAttribute("value","none");
                break;
            case POWER_SUPPLY:
                model.addAttribute("Component",new PowerSupply());
                model.addAttribute("value","none");
                break;
            case SOFTWARE:
                model.addAttribute("Component",new Software());
                model.addAttribute("value","none");
                break;
            case STORAGE:
                model.addAttribute("Component",new Storage());
                model.addAttribute("value","GB");
                break;
            case VIDEO_CARD:
                model.addAttribute("Component",new VideoCard());
                model.addAttribute("value","none");
                break;
            case EXTERNAL_STORAGE:
                model.addAttribute("Component",new ExternalStorage());
                model.addAttribute("value","GB");
                break;

        }

        return "hardware/add-hardware";
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
        return "redirect:/hardware/new-item";
    }
}

