package com.palta.BuildRig.controller;

import com.palta.BuildRig.Models.*;
import com.palta.BuildRig.data.*;
import com.palta.BuildRig.forms.hardwareType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import javax.management.monitor.Monitor;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping(value = "buildRig")
public class BuildController {

    @Autowired
    RigDao rigDao;
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


    @RequestMapping(value = "",  method = RequestMethod.GET)
    public String rigList(Model model) {

        model.addAttribute("title", "Rigs");
        model.addAttribute("rigs", rigDao.findAll());
        return "rig/list";
    }



    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String addRig(Model model){
        model.addAttribute("title", "Rig Name");
        model.addAttribute(new Rig());

        return "rig/name-rig";
    }


    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String add(Model model, @ModelAttribute @Valid Rig rig, HttpSession session) {


        rigDao.save(rig);
        return "redirect:/buildRig/rig/" + rig.getId();
    }


    @RequestMapping(value = "/rig/{rigId}",  method = RequestMethod.GET)
    public String creationPage(Model model, @PathVariable int rigId) {

        hardwareType[] hardwareEnums = hardwareType.values();
        Rig foundRig = rigDao.findOne(rigId);

        model.addAttribute("totalPrice",foundRig.getPrice());
        model.addAttribute("Memory",foundRig.getPcMemory());
        model.addAttribute("ProcessingSpeed",foundRig.getProcessingSpeed());

        model.addAttribute("rigItems",foundRig);
        model.addAttribute("title", "Build-a-rig");
        model.addAttribute("hardwareType", hardwareEnums);
        model.addAttribute("rigId", rigId);
        return "/rig/rigBuilder";
    }

    @RequestMapping(value = "add-hardware", method = RequestMethod.GET)
    public String hardwarePick(Model model, @RequestParam hardwareType hardwareEnums, @RequestParam int rigId) {

        Rig rig = rigDao.findOne(rigId);
        model.addAttribute("title", hardwareEnums.getName());
        model.addAttribute("Hardware", hardwareEnums);
        model.addAttribute("foundRig",rig);

        switch (hardwareEnums){
            case CPU:
                Iterable<Cpu> allCpu = cpuDao.findAll();
                model.addAttribute("findHardware", allCpu);
                model.addAttribute("symbol","GHz");
                break;

            case CPU_COOLER:
                Iterable<CpuCooler> allCpuCooler = cpuCoolerDao.findAll();
                model.addAttribute("findHardware", allCpuCooler);
                break;

            case MEMORY:
                Iterable<Memory> allMemory = memoryDao.findAll();
                model.addAttribute("findHardware", allMemory);
                model.addAttribute("symbol","TB");
                break;

            case MOTHERBOARD:
                Iterable<MotherBoard> allMotherBoard = motherBoardDao.findAll();
                model.addAttribute("findHardware", allMotherBoard);
                break;

            case EXTERNAL_STORAGE:
                Iterable<ExternalStorage> allExternalStorage = externalStorageDao.findAll();
                model.addAttribute("findHardware", allExternalStorage);
                break;

            case OPERATING_SYSTEM:
                Iterable<OperatingSystem> allOperatingSystem = operatingSystemDao.findAll();
                model.addAttribute("findHardware", allOperatingSystem);
                break;

            case OPTICAL_DRIVE:
                Iterable<OpticalDrive> allOpticalDrive = opticalDriveDao.findAll();
                model.addAttribute("findHardware", allOpticalDrive);
                break;

            case CASE:
                Iterable<PcCase> allCase = pcCaseDao.findAll();
                model.addAttribute("findHardware", allCase);
                break;

            case MONITOR:
                Iterable<PcMonitor> allMonitor = pcMonitorDao.findAll();
                model.addAttribute("findHardware", allMonitor);
                break;

            case POWER_SUPPLY:
                Iterable<PowerSupply> allPowerSupply = powerSupplyDao.findAll();
                model.addAttribute("findHardware", allPowerSupply);
                break;

            case SOFTWARE:
                Iterable<Software> allSoftware = softwareDao.findAll();
                model.addAttribute("findHardware", allSoftware);
                break;

            case STORAGE:
                Iterable<Storage> allStorage = storageDao.findAll();
                model.addAttribute("findHardware", allStorage);
                break;

            case VIDEO_CARD:
                Iterable<VideoCard> allVideoCard = videoCardDao.findAll();
                model.addAttribute("findHardware", allVideoCard);
                break;
        }

        return "/rig/add-hardware";
    }

    @RequestMapping(value = "add-hardware-process", method = RequestMethod.GET)
    public String addingProcess(Model model,
                                @RequestParam int hardwareId,
                                @RequestParam int rigId,
                                @RequestParam hardwareType hardwareEnums){

        Rig rig = rigDao.findOne(rigId);

        switch (hardwareEnums){
            case CPU:
            Cpu cpu = cpuDao.findOne(hardwareId);
            rig.setCpu(cpu);
            break;

            case CPU_COOLER:
            CpuCooler cpuCooler = cpuCoolerDao.findOne(hardwareId);
            rig.setCpuCooler(cpuCooler);
            break;

            case MEMORY:
            Memory memory = memoryDao.findOne(hardwareId);
            rig.setMemory(memory);
            break;

            case MOTHERBOARD:
            MotherBoard motherBoard = motherBoardDao.findOne(hardwareId);
            rig.setMotherBoard(motherBoard);
            break;

            case EXTERNAL_STORAGE:
                ExternalStorage externalStorage = externalStorageDao.findOne(hardwareId);
                rig.setExternalStorage(externalStorage);
                break;

            case OPERATING_SYSTEM:
                OperatingSystem operatingSystem = operatingSystemDao.findOne(hardwareId);
                rig.setOperatingSystem(operatingSystem);
                break;

            case OPTICAL_DRIVE:
                OpticalDrive opticalDrive = opticalDriveDao.findOne(hardwareId);
                rig.setOpticalDrive(opticalDrive);
                break;

            case CASE:
                PcCase pcCase = pcCaseDao.findOne(hardwareId);
                rig.setPcCase(pcCase);
                break;

            case MONITOR:
                PcMonitor pcMonitor = pcMonitorDao.findOne(hardwareId);
                rig.setPcMonitor(pcMonitor);
                break;

            case POWER_SUPPLY:
                PowerSupply powerSupply = powerSupplyDao.findOne(hardwareId);
                rig.setPowerSupply(powerSupply);
                break;

            case SOFTWARE:
                Software software = softwareDao.findOne(hardwareId);
                rig.setSoftware(software);
                break;

            case STORAGE:
                Storage storage = storageDao.findOne(hardwareId);
                rig.setStorage(storage);
                break;

            case VIDEO_CARD:
                VideoCard videoCard = videoCardDao.findOne(hardwareId);
                rig.setVideoCard(videoCard);
                break;
        }

        double Price = 0;
        double Memory = 0;
        double ProcessingSpeed = 0;
//----------------------------------------------------------ADD PRICES
        if (rig.getCpu() != null){
            Price += rig.getCpu().getItemPrice();
            ProcessingSpeed += rig.getCpu().getPcValue();
        }
        if (rig.getCpuCooler() != null){
            Price += rig.getCpuCooler().getItemPrice();
        }
        if (rig.getMemory() != null){
            Price += rig.getMemory().getItemPrice();
            Memory += rig.getMemory().getPcValue();
        }
        if (rig.getMotherBoard() != null){
            Price += rig.getMotherBoard().getItemPrice();
        }
        if (rig.getExternalStorage() != null){
            Price += rig.getExternalStorage().getItemPrice();
        }
        if (rig.getOperatingSystem() != null){
            Price += rig.getOperatingSystem().getItemPrice();
        }
        if (rig.getOpticalDrive() != null){
            Price += rig.getOpticalDrive().getItemPrice();
        }
        if (rig.getPcCase() != null){
            Price += rig.getPcCase().getItemPrice();
        }
        if (rig.getPcMonitor() != null){
            Price += rig.getPcMonitor().getItemPrice();
        }
        if (rig.getPowerSupply() != null){
            Price += rig.getPowerSupply().getItemPrice();
        }
        if (rig.getSoftware() != null){
            Price += rig.getSoftware().getItemPrice();
        }
        if (rig.getStorage() != null){
            Price += rig.getStorage().getItemPrice();
        }
        if (rig.getVideoCard() != null){
            Price += rig.getVideoCard().getItemPrice();
        }
//----------------------------------------------------------ADD PRICES

        rig.setPrice(Price);
        rig.setPcMemory(Memory);
        rig.setProcessingSpeed(ProcessingSpeed);
        rigDao.save(rig);
        return "redirect:/buildRig/rig/" + rig.getId();
    }

}
