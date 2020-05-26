package com.ifaproject.CourseChevaux.rest;


import com.ifaproject.CourseChevaux.rest.exception.NotFoundException;
import com.ifaproject.CourseChevaux.dao.ProprietaireDao;
import com.ifaproject.CourseChevaux.entity.Proprietaire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
public class ProprietaireController {


    @Autowired
    ProprietaireDao proprietaireDao;

    //mapping Get / proprietaires
    @GetMapping("/proprietaires")
    public List<Proprietaire> getProprietaire(){
        return proprietaireDao.findAll();
    }

    //mapping Get /  proprietaire par id
    @GetMapping("/proprietaire/{nomProprietaire}")
    public Optional<Proprietaire> getProprietaire(@PathVariable String nomProprietaire){
        Optional<Proprietaire> proprietaire = proprietaireDao.findById(nomProprietaire);
        if(!proprietaire.isPresent()){
            throw new NotFoundException("Propriétaire introuvable - " + nomProprietaire);
        }
        return proprietaire;
    }

    //mapping POST / proprietaire - add new proprietaire
    @PostMapping("/proprietaire")
    public Proprietaire addProprietaire(@RequestBody Proprietaire proprietaire){
        proprietaireDao.save(proprietaire);
        return proprietaire;
    }

    //mapping PUT / proprietaire - update proprietaire
    @PutMapping("/proprietaire")
    public Proprietaire updateProprietaire(@RequestBody Proprietaire proprietaire){
        proprietaireDao.save(proprietaire);
        return proprietaire;
    }

    //mapping DELETE / proprietaire - delete proprietaire
    @DeleteMapping("/proprietaire/{nomProprietaire}")
    public String deleteProprietaire(@PathVariable String nomProprietaire){
        Optional<Proprietaire> proprietaire = proprietaireDao.findById(nomProprietaire);
        //throw exception SI null
        if(!proprietaire.isPresent()){
            throw new NotFoundException("Propriétaire introuvable - " + nomProprietaire);
        }
        proprietaireDao.deleteById(nomProprietaire);
        return "Suppression du propriétaire : " + nomProprietaire;
    }




}
