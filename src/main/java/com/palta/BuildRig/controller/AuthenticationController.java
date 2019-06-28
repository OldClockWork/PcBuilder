package com.palta.BuildRig.controller;

import com.palta.BuildRig.Models.User;
import com.palta.BuildRig.data.UserDao;
import org.hibernate.validator.constraints.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping(value = "account")
public class AuthenticationController {


    @Autowired
    UserDao userDao;

    @RequestMapping(value = "register", method = RequestMethod.GET)
    public String register(Model model){

        model.addAttribute("title","Register");
        model.addAttribute("newUser",new User());
        return "register";
    }

    @RequestMapping(value = "register", method = RequestMethod.POST)
    public String registerProcess(Model model, User user, Errors errors){

        if (errors.hasErrors()){
            model.addAttribute("title","Register");
            model.addAttribute("newUser",new User());
            return "register";
        } else {
            userDao.save(user);
            return "redirect:/buildRig";
        }

    }

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String login(Model model, User user){


        model.addAttribute("title","Login");
        model.addAttribute("userLogin", new User());
        return "login";
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String loginProcess(HttpSession session, @Valid User user){

        User theUser = userDao.findByEmail(user.getEmail());
        session.setAttribute("currentUser", theUser);


        return "redirect:/buildRig";
    }

}
