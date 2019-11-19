package com.iuli.bookclassroom.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.net.MalformedURLException;

@Controller
public class MainController {
    @Autowired
    PasswordEncoder pEncoder;
    @RequestMapping("/")
    public ModelAndView main(ModelAndView mView,
                             @ModelAttribute(name = "result_code") String result_code,
                             @ModelAttribute(name = "result_message") String result_message){
        System.out.println(pEncoder.encode("fariz")); //TODO delete this line.
        mView.setViewName("pages/home/index");
        return mView;
    }
}