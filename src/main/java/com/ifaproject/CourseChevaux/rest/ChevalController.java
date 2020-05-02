package com.ifaproject.CourseChevaux.rest;


import com.ifaproject.CourseChevaux.rest.exception.NotFoundException;
import com.ifaproject.CourseChevaux.dao.ChevalDao;
import com.ifaproject.CourseChevaux.entity.Cheval;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
//@RequestMapping("/cheval")
public class ChevalController {

    @Autowired
    ChevalDao chevalDao;

    //mapping Get / chevaux
    @GetMapping("/cheval")
    public List<Cheval> getChevaux(){
        return chevalDao.findAll();
    }

    //mapping Get /  cheval par id
    @GetMapping("/cheval/{nomCheval}")
    public Optional<Cheval> getCheval(@PathVariable String nomCheval){
        Optional<Cheval> cheval = chevalDao.findById(nomCheval);
        if(!cheval.isPresent()){
            throw new NotFoundException("Cheval introuvable - " + nomCheval);
        }
        return cheval;
    }

    //mapping POST / cheval - add new cheval
    @PostMapping("/cheval")
    public Cheval addCheval(@RequestBody Cheval cheval){
        chevalDao.save(cheval);
        return cheval;
    }

    //mapping PUT / cheval - update cheval
    @PutMapping("/cheval")
    public Cheval updateCheval(@RequestBody Cheval cheval){
        chevalDao.save(cheval);
        return cheval;
    }

    //mapping DELETE / cheval - delete cheval
    @DeleteMapping("/cheval/{nomCheval}")
    public String deleteCheval(@PathVariable String nomCheval){
        Optional<Cheval> cheval = chevalDao.findById(nomCheval);
        //throw exception SI null
        if(!cheval.isPresent()){
            throw new NotFoundException("Cheval introuvable - " + nomCheval);
        }
        chevalDao.deleteById(nomCheval);
        return "Suppression du cheval : " + nomCheval;
    }



}
