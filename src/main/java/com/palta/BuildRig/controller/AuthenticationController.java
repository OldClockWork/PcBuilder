package com.palta.BuildRig.controller;

import com.palta.BuildRig.Models.User;
import com.palta.BuildRig.data.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping(value = "account")
public class AuthenticationController {


    @Autowired
    UserDao userDao;

    @RequestMapping(value = "register", method = RequestMethod.GET)
    public String register(Model model){

        model.addAttribute(new User());
        model.addAttribute("title","REGISTER");
        return "register";
    }

    @RequestMapping(value = "register", method = RequestMethod.POST)
    public String registerProcess(Model model, @ModelAttribute @Valid User user, Errors errors,
                                  HttpSession session, @RequestParam String passVal){



        if (errors.hasErrors()){
            model.addAttribute("title","REGISTER");
            return "register";
        }

        if (!user.getPassword().equals(passVal)){
            model.addAttribute("title","REGISTER");
            model.addAttribute("valError","Password did not match");
            return "register";
        }

        User emailCheck = userDao.findByEmail(user.getEmail());
        User nameCheck = userDao.findByUsername(user.getUsername());


        if (emailCheck != null){
            model.addAttribute("title","REGISTER");
            model.addAttribute("emailError","Email is already in use");
            return "register";
        }

        if (nameCheck != null){
            model.addAttribute("title","REGISTER");
            model.addAttribute("userError","Name is already in use");
            return "register";
        }

        userDao.save(user);
        session.setAttribute("currentUser", user.getEmail());
        return "redirect:/buildRig";

    }

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String login(Model model, User user){


        model.addAttribute(new User());
        model.addAttribute("title","LOGIN");
        return "login";
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String loginProcess(Model model, HttpSession session, User user){




        if (userDao.findByEmail(user.getEmail()) == null){
            model.addAttribute("title","LOGIN");
            model.addAttribute("emailError","Email does not exist");
            return "login";
        }

        User theUser = userDao.findByEmail(user.getEmail());

        if(!user.getPassword().equals(theUser.getPassword())){
            model.addAttribute("title","LOGIN");
            model.addAttribute("passwordError","Incorrect password");
            return "login";
        }
        else {

            session.setAttribute("currentUser", theUser.getEmail());
            return "redirect:/buildRig";
        }
    }

    @RequestMapping(value = "logout")
    public String logoutProcess(HttpSession session){
        session.removeAttribute("currentUser");

        return "redirect:/";
    }

}
