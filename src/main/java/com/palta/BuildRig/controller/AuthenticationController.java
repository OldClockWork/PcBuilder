package com.palta.BuildRig.controller;

import com.palta.BuildRig.Models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "account")
public class AuthenticationController {


    @RequestMapping(value = "register", method = RequestMethod.GET)
    public String register(Model model){

        model.addAttribute("title","Register");
        model.addAttribute("newUser",new User());
        return "register";
    }

    @RequestMapping(value = "register", method = RequestMethod.POST)
    public String registerProcess(Model model){


        return "redirect:/login";
    }

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String login(Model model){


        return "login";
    }
}
