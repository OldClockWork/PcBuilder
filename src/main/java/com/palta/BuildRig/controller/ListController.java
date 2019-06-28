package com.palta.BuildRig.controller;


import com.palta.BuildRig.Models.Rig;
import com.palta.BuildRig.data.RigDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "lists")
public class ListController {

    @Autowired
    RigDao rigDao;

    @RequestMapping(value = "wishlist")
    public String wishlist(Model model){


        model.addAttribute("title","Wishlist");
        model.addAttribute("rigs", rigDao.findAll());
        return "lists/wishlist";
    }


    @RequestMapping(value = "/wishlistRigItems/{rigId}")
    public String rigItems(Model model, @PathVariable int rigId){

        Rig rig = rigDao.findOne(rigId);
        model.addAttribute("title",rig.getName());
        model.addAttribute("rigParts",rig);
        return "lists/rigItems";
    }
}
