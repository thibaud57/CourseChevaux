package com.ifaproject.CourseChevaux.rest;


import com.ifaproject.CourseChevaux.rest.exception.NotFoundException;
import com.ifaproject.CourseChevaux.dao.JockeyDao;
import com.ifaproject.CourseChevaux.entity.Jockey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
//@RequestMapping("/jockey")
public class JockeyController {

    @Autowired
    JockeyDao jockeyDao;

    //mapping Get / jockeys
    @GetMapping("/jockey")
    public List<Jockey> getJockeys(){
        return jockeyDao.findAll();
    }

    //mapping Get /  jockey par id
    @GetMapping("/jockey/{matricule}")
    public Optional<Jockey> getJockey(@PathVariable int matricule){
        Optional<Jockey> jockey = jockeyDao.findById(matricule);
        if(!jockey.isPresent()){
            throw new NotFoundException("Jockey introuvable - " + matricule);
        }
        return jockey;
    }

    //mapping POST / jockey - add new jockey
    @PostMapping("/jockey")
    public Jockey addJockey(@RequestBody Jockey jockey){
        jockeyDao.save(jockey);
        return jockey;
    }

    //mapping PUT / jockey - update jockey
    @PutMapping("/jockey")
    public Jockey updateJockey(@RequestBody Jockey jockey){
        jockeyDao.save(jockey);
        return jockey;
    }

    //mapping DELETE / jockey - delete jockey
    @DeleteMapping("/jockey/{matricule}")
    public String deleteJockey(@PathVariable int matricule){
        Optional<Jockey> jockey = jockeyDao.findById(matricule);
        //throw exception SI null
        if(!jockey.isPresent()){
            throw new NotFoundException("Jockey introuvable - " + matricule);
        }
        jockeyDao.deleteById(matricule);
        return "Suppression du jockey : " + matricule;
    }



}
