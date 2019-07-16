package com.palta.BuildRig.controller;


import com.palta.BuildRig.Models.Pc;
import com.palta.BuildRig.Models.User;
import com.palta.BuildRig.data.PcDao;
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
@RequestMapping(value = "pcCompare")
public class CompareController {

    @Autowired
    UserDao userDao;
    @Autowired
    PcDao pcDao;

    @RequestMapping(value = "compare", method = RequestMethod.GET)
    public String compair(Model model, HttpSession session){

        if (session.getAttribute("currentUser") == null) {
            return "redirect:/account/login";
        }

        String username = (String) session.getAttribute("currentUser");
        User user = userDao.findByEmail(username);
        List<Pc> userPcs = pcDao.findByUser(user);

        model.addAttribute("mainRig", userPcs);
        model.addAttribute("otherRig", userPcs);

        return "compare/index";
    }

    @RequestMapping(value = "compare", method = RequestMethod.POST)
    public String compairingProcess(Model model, @RequestParam int mainRig, @RequestParam int otherRig){

        Pc Pc1 = pcDao.findOne(mainRig);
        Pc Pc2 = pcDao.findOne(otherRig);

        double Price = Pc1.getPrice() - Pc2.getPrice();
        double ProcessingSpeed = Pc1.getProcessingSpeed() - Pc2.getProcessingSpeed();
        double Storage = Pc1.getPcStorage() - Pc2.getPcStorage();

        if(Price < 0){
            model.addAttribute("priceWarning","Your current pc will save you:");
            Price *= -1;
        }
        else if (Price > 0){
            model.addAttribute("priceWarning","Your current pc will cost you an extra:");
        } else {
            model.addAttribute("priceWarning","No difference in price");
        }


        if (ProcessingSpeed < 0){
            model.addAttribute("processWarning", "Your current pc runs slower by:");
            ProcessingSpeed *= -1;
        }
        else if(ProcessingSpeed > 0){
            model.addAttribute("processWarning", "Your current pc runs faster by:");
        } else {
            model.addAttribute("processWarning", "No difference in processing speed");
        }

        if (Storage < 0){
            model.addAttribute("storageWarning", "You current pc has less storage by:");
            Storage *= -1;
        }
        else if (Storage > 0){
            model.addAttribute("storageWarning", "You current pc has more storage by:");
        } else {
            model.addAttribute("storageWarning", "No difference in storage space");
        }

        model.addAttribute("mainName", Pc1.getName());
        model.addAttribute("mainPrice", Pc1.getPrice());
        model.addAttribute("mainSpeed", Pc1.getProcessingSpeed());
        model.addAttribute("mainStorage", Pc1.getPcStorage());

        model.addAttribute("otherName", Pc2.getName());
        model.addAttribute("otherPrice", Pc2.getPrice());
        model.addAttribute("otherSpeed", Pc2.getProcessingSpeed());
        model.addAttribute("otherStorage", Pc2.getPcStorage());

        model.addAttribute("price", Price);
        model.addAttribute("processingSpeed", ProcessingSpeed);
        model.addAttribute("storage", Storage);

        return "compare/compared";
    }
}
