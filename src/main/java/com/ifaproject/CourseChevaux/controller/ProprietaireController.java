package com.ifaproject.CourseChevaux.controller;


import com.ifaproject.CourseChevaux.dao.ProprietaireDao;
import com.ifaproject.CourseChevaux.entity.Proprietaire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class ProprietaireController {

    @Autowired
    ProprietaireDao proprietaireDao;

    public void saveProprietaire(Proprietaire proprietaire){
        proprietaireDao.save(proprietaire);
    }

}
