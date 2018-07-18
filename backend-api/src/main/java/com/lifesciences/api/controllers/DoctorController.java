package com.lifesciences.api.controllers;

import java.util.List;

import com.lifesciences.api.dao.DoctorDao;
import com.lifesciences.api.model.Doctor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/doctor")
public class DoctorController {

    @Autowired
    private DoctorDao dao;

    @PostMapping("/all")
    public List<Doctor> getAllDoctors() {
        List<Doctor> doctors = null;
        
        doctors = dao.getDoctors();
        return doctors;
    }

    @PostMapping("/{docId}")
    public List<Doctor> getDoctorDetails(@PathVariable int docId) {
        List<Doctor> doctors = null;
        
        doctors = dao.getDoctorsDetails(docId);
        return doctors;
    }

    @PostMapping("/register")
    public @ResponseBody Doctor register(@RequestBody Doctor doc) {
        dao.createDoctor(doc);
        return doc;
    }

    @PostMapping("/update")
    public @ResponseBody Doctor updateDoctorDetails(@RequestBody Doctor doc) {
        dao.updateDoctor(doc);
        return doc;
    }
}