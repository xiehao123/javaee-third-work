package com.fitnessclub.demo.Service;

import com.fitnessclub.demo.Entity.Information;
import com.fitnessclub.demo.Repository.InformationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UIservice {

    @Autowired
    private InformationRepo userservice;


    public void save(Information info){
        userservice.save(info);
    }

    public List<Information> getall(){
        return userservice.findAll();
    }

}
