package com.ifaproject.CourseChevaux.controller;


import com.ifaproject.CourseChevaux.dao.ChevalDao;
import com.ifaproject.CourseChevaux.entity.Cheval;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/cheval")
public class ChevalController {

    @Autowired
    ChevalDao chevalDao;

    //Créer un cheval
    @PutMapping("/cree")
    public void saveCheval(@RequestBody Cheval cheval){
        chevalDao.save(cheval);
    }



}
