package com.lifesciences.api.controllers;

import java.util.List;

import com.lifesciences.api.dao.DoctorDao;
import com.lifesciences.api.model.Doctor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DoctorController {

    @Autowired
    private DoctorDao dao;

    @RequestMapping("/doctors")
    public List<Doctor> getDoctors() {
        List<Doctor> doctors = null;
        
        doctors = dao.getDoctorsDetails(111);
        return doctors;
        
    }
}