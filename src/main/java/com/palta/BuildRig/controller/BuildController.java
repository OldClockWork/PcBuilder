package com.palta.BuildRig.controller;

import com.palta.BuildRig.Models.Cpu;
import com.palta.BuildRig.Models.CpuCooler;
import com.palta.BuildRig.Models.Rig;
import com.palta.BuildRig.data.CpuCoolerDao;
import com.palta.BuildRig.data.CpuDao;
import com.palta.BuildRig.data.RigDao;
import com.palta.BuildRig.forms.hardwareType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


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
    public String add(Model model, @ModelAttribute @Valid Rig rig) {

        rigDao.save(rig);
        return "redirect:/buildRig/rig/" + rig.getId();
    }


    @RequestMapping(value = "/rig/{rigId}",  method = RequestMethod.GET)
    public String creationPage(Model model, @PathVariable int rigId) {

        hardwareType[] hardwareEnums = hardwareType.values();
        Rig foundRig = rigDao.findOne(rigId);

        int Price = 0;



        if (foundRig.getCpu() != null){
            Price += foundRig.getCpu().getItemPrice();
        }
        if (foundRig.getCpuCooler() != null){
            Price += foundRig.getCpuCooler().getItemPrice();
        }


        model.addAttribute("totalPrice",Price);
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
                break;

            case CPU_COOLER:
                Iterable<CpuCooler> allCpuCooler = cpuCoolerDao.findAll();
                model.addAttribute("findHardware", allCpuCooler);
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
        }

        int Price = 0;
        if (rig.getCpu() != null){
            Price += rig.getCpu().getItemPrice();
        }
        if (rig.getCpuCooler() != null){
            Price += rig.getCpuCooler().getItemPrice();
        }

        rig.setPrice(Price);
        rigDao.save(rig);
        return "redirect:/buildRig/rig/" + rig.getId();
    }

}
