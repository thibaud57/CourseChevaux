package com.ifaproject.CourseChevaux.controller;


import com.ifaproject.CourseChevaux.dao.JockeyDao;
import com.ifaproject.CourseChevaux.entity.Jockey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/jockey")
public class JockeyController {

    @Autowired
    JockeyDao jockeyDao;

    //Créer un jockey
    @PutMapping("/cree")
    public void saveJockey(@RequestBody Jockey jockey){
        jockeyDao.save(jockey);
    }

}
