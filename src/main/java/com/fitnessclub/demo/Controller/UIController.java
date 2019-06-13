package com.fitnessclub.demo.Controller;

import com.fitnessclub.demo.Entity.Information;
import com.fitnessclub.demo.Service.UIservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class UIController {

    @Autowired
    public UIservice service;

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String postUI(){
        return "information.html";
    }

    @RequestMapping(value = "/look",method = RequestMethod.GET)
    public String look(Model model){
        System.out.println("进来了");
        List<Information> list = service.getall();
        model.addAttribute("information", list);
        return "look_up.html";
    }

}
