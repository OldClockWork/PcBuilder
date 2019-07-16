package com.palta.BuildRig.controller;
import com.palta.BuildRig.Models.*;
import com.palta.BuildRig.data.*;
import com.palta.BuildRig.forms.hardwareType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.text.DecimalFormat;
import java.util.List;

@Controller
@RequestMapping(value = "buildRig")
public class BuildController {

    @Autowired
    UserDao userDao;
    @Autowired
    PcDao pcDao;
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
    public String rigList(Model model, HttpSession session) {

        if (session.getAttribute("currentUser") == null) {
            return "redirect:/account/login";
        }

        String username = (String) session.getAttribute("currentUser");
        User user = userDao.findByEmail(username);
        List<Pc> userRigs = pcDao.findByUser(user);

        model.addAttribute("title", "PC Designs");
        model.addAttribute("pcs", userRigs);
        return "pc/list";
    }


    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String addRig(Model model, HttpSession session){

        if (session.getAttribute("currentUser") == null) {
            return "redirect:/account/login";
        }

        model.addAttribute("title", "New PC");
        model.addAttribute(new Pc());

        return "pc/name-pc";
    }


    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String add(Model model, @ModelAttribute @Valid Pc pc, HttpSession session) {

        if (session.getAttribute("currentUser") == null) {
            return "redirect:/account/login";
        }


        String username = (String) session.getAttribute("currentUser");
        User user = userDao.findByEmail(username);
        pc.setUser(user);
        pcDao.save(pc);
        return "redirect:/buildRig/pc/" + pc.getId();
    }


    @RequestMapping(value = "/pc/{pcId}",  method = RequestMethod.GET)
    public String creationPage(Model model, @PathVariable int pcId, HttpSession session) {

        if (session.getAttribute("currentUser") == null) {
            return "redirect:/account/login";
        }

        hardwareType[] hardwareEnums = hardwareType.values();
        Pc foundRig = pcDao.findOne(pcId);

        if(!foundRig.getUser().getEmail().equals(session.getAttribute("currentUser"))){
            return "redirect:/buildRig";

        }


        DecimalFormat goodFormat = new DecimalFormat("0.00");

        model.addAttribute("totalPrice", goodFormat.format(foundRig.getPrice()));
        model.addAttribute("Storage",goodFormat.format(foundRig.getPcStorage()));
        model.addAttribute("ProcessingSpeed", goodFormat.format(foundRig.getProcessingSpeed()));

        model.addAttribute("rigItems",foundRig);
        model.addAttribute("title", "Build PC");
        model.addAttribute("hardwareType", hardwareEnums);
        model.addAttribute("pcId", pcId);
        return "/pc/rigBuilder";
    }

    @RequestMapping(value = "add-hardware", method = RequestMethod.GET)
    public String hardwarePick(Model model, @RequestParam hardwareType hardwareEnums, @RequestParam int pcId,
                               HttpSession session) {

        if (session.getAttribute("currentUser") == null) {
            return "redirect:/account/login";
        }

        Pc pc = pcDao.findOne(pcId);

        if(!pc.getUser().getEmail().equals(session.getAttribute("currentUser"))){
            return "redirect:/buildRig";
        }

        model.addAttribute("title", hardwareEnums.getName());
        model.addAttribute("Hardware", hardwareEnums);
        model.addAttribute("foundRig",pc);

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

                break;

            case MOTHERBOARD:
                Iterable<MotherBoard> allMotherBoard = motherBoardDao.findAll();
                model.addAttribute("findHardware", allMotherBoard);
                break;

            case EXTERNAL_STORAGE:
                Iterable<ExternalStorage> allExternalStorage = externalStorageDao.findAll();
                model.addAttribute("findHardware", allExternalStorage);
                model.addAttribute("symbol","GB");
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
                model.addAttribute("symbol","GB");
                break;

            case VIDEO_CARD:
                Iterable<VideoCard> allVideoCard = videoCardDao.findAll();
                model.addAttribute("findHardware", allVideoCard);
                break;
        }

        return "/pc/add-hardware";
    }

    @RequestMapping(value = "add-hardware-process", method = RequestMethod.GET)
    public String addingProcess(Model model,
                                @RequestParam int hardwareId,
                                @RequestParam int pcId,
                                @RequestParam hardwareType hardwareEnums){

        Pc pc = pcDao.findOne(pcId);

        switch (hardwareEnums){
            case CPU:
            Cpu cpu = cpuDao.findOne(hardwareId);
            pc.setCpu(cpu);
            break;

            case CPU_COOLER:
            CpuCooler cpuCooler = cpuCoolerDao.findOne(hardwareId);
                pc.setCpuCooler(cpuCooler);
            break;

            case MEMORY:
            Memory memory = memoryDao.findOne(hardwareId);
                pc.setMemory(memory);
            break;

            case MOTHERBOARD:
            MotherBoard motherBoard = motherBoardDao.findOne(hardwareId);
                pc.setMotherBoard(motherBoard);
            break;

            case EXTERNAL_STORAGE:
                ExternalStorage externalStorage = externalStorageDao.findOne(hardwareId);
                pc.setExternalStorage(externalStorage);
                break;

            case OPERATING_SYSTEM:
                OperatingSystem operatingSystem = operatingSystemDao.findOne(hardwareId);
                pc.setOperatingSystem(operatingSystem);
                break;

            case OPTICAL_DRIVE:
                OpticalDrive opticalDrive = opticalDriveDao.findOne(hardwareId);
                pc.setOpticalDrive(opticalDrive);
                break;

            case CASE:
                PcCase pcCase = pcCaseDao.findOne(hardwareId);
                pc.setPcCase(pcCase);
                break;

            case MONITOR:
                PcMonitor pcMonitor = pcMonitorDao.findOne(hardwareId);
                pc.setPcMonitor(pcMonitor);
                break;

            case POWER_SUPPLY:
                PowerSupply powerSupply = powerSupplyDao.findOne(hardwareId);
                pc.setPowerSupply(powerSupply);
                break;

            case SOFTWARE:
                Software software = softwareDao.findOne(hardwareId);
                pc.setSoftware(software);
                break;

            case STORAGE:
                Storage storage = storageDao.findOne(hardwareId);
                pc.setStorage(storage);
                break;

            case VIDEO_CARD:
                VideoCard videoCard = videoCardDao.findOne(hardwareId);
                pc.setVideoCard(videoCard);
                break;
        }

        double Price = 0;
        double Storage = 0;
        double ProcessingSpeed = 0;
//----------------------------------------------------------ADD PRICES
        if (pc.getCpu() != null){
            Price += pc.getCpu().getItemPrice();
            ProcessingSpeed += pc.getCpu().getPcValue();
        }
        if (pc.getCpuCooler() != null){
            Price += pc.getCpuCooler().getItemPrice();
        }
        if (pc.getMemory() != null){
            Price += pc.getMemory().getItemPrice();
        }
        if (pc.getMotherBoard() != null){
            Price += pc.getMotherBoard().getItemPrice();
        }
        if (pc.getExternalStorage() != null){
            Price += pc.getExternalStorage().getItemPrice();
            Storage += pc.getExternalStorage().getPcValue();
        }
        if (pc.getOperatingSystem() != null){
            Price += pc.getOperatingSystem().getItemPrice();
        }
        if (pc.getOpticalDrive() != null){
            Price += pc.getOpticalDrive().getItemPrice();
        }
        if (pc.getPcCase() != null){
            Price += pc.getPcCase().getItemPrice();
        }
        if (pc.getPcMonitor() != null){
            Price += pc.getPcMonitor().getItemPrice();
        }
        if (pc.getPowerSupply() != null){
            Price += pc.getPowerSupply().getItemPrice();
        }
        if (pc.getSoftware() != null){
            Price += pc.getSoftware().getItemPrice();
        }
        if (pc.getStorage() != null){
            Price += pc.getStorage().getItemPrice();
            Storage += pc.getStorage().getPcValue();
        }
        if (pc.getVideoCard() != null){
            Price += pc.getVideoCard().getItemPrice();
        }
//----------------------------------------------------------ADD PRICES

        pc.setPrice(Price);
        pc.setPcStorage(Storage);
        pc.setProcessingSpeed(ProcessingSpeed);
        pcDao.save(pc);
        return "redirect:/buildRig/pc/" + pc.getId();
    }

}
