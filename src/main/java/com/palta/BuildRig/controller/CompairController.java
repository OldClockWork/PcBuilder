package com.palta.BuildRig.controller;


import com.palta.BuildRig.Models.Rig;
import com.palta.BuildRig.Models.User;
import com.palta.BuildRig.data.RigDao;
import com.palta.BuildRig.data.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping(value = "pcCompair")
public class CompairController {

    @Autowired
    UserDao userDao;
    @Autowired
    RigDao rigDao;

    @RequestMapping(value = "compair", method = RequestMethod.GET)
    public String compair(Model model, HttpSession session){


        String username = (String) session.getAttribute("currentUser");
        User user = userDao.findByEmail(username);
        List<Rig> userRigs = rigDao.findByUser(user);

        model.addAttribute("mainRig", userRigs);
        model.addAttribute("otherRig", userRigs);

        return "compair/index";
    }

    @RequestMapping(value = "compair", method = RequestMethod.POST)
    public String compairingProcess(Model model, @RequestParam int mainRig, @RequestParam int otherRig){

        Rig rig1 = rigDao.findOne(mainRig);
        Rig rig2 = rigDao.findOne(otherRig);

        double Price = rig1.getPrice() - rig2.getPrice();
        double ProcessingSpeed = rig1.getProcessingSpeed() - rig2.getProcessingSpeed();
        double Storage = rig1.getPcStorage() - rig2.getPcStorage();

        if(Price < 0){
            model.addAttribute("priceWarning","Your current rig will save you:");
            Price *= -1;
        }
        else if (Price > 0){
            model.addAttribute("priceWarning","Your current rig will cost you an extra:");
        } else {
            model.addAttribute("priceWarning","No value difference in price");
        }


        if (ProcessingSpeed < 0){
            model.addAttribute("processWarning", "Your current rig runs slower by:");
            ProcessingSpeed *= -1;
        }
        else if(ProcessingSpeed > 0){
            model.addAttribute("processWarning", "Your current rig runs faster by:");
        } else {
            model.addAttribute("processWarning", "No value difference in processing speed");
        }

        if (Storage < 0){
            model.addAttribute("storageWarning", "You current rig has less storage by:");
            Storage *= -1;
        }
        else if (Storage > 0){
            model.addAttribute("storageWarning", "You current rig has more storage by:");
        } else {
            model.addAttribute("storageWarning", "No value difference storage");
        }

        model.addAttribute("price", Price);
        model.addAttribute("processingSpeed", ProcessingSpeed);
        model.addAttribute("storage", Storage);

        return "compair/compaired";
    }
}
