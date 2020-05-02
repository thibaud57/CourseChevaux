package com.ifaproject.CourseChevaux.rest;

import com.ifaproject.CourseChevaux.rest.exception.NotFoundException;
import com.ifaproject.CourseChevaux.dao.ChampsCourseDao;
import com.ifaproject.CourseChevaux.entity.ChampsCourse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
//@RequestMapping("/champs-de-course")
public class ChampsCourseController {


    @Autowired
    ChampsCourseDao champsCourseDao;

    //mapping Get / champsCourses
    @GetMapping("/champsCourse")
    public List<ChampsCourse> getChampsCourse(){
        return champsCourseDao.findAll();
    }

    //mapping Get /  champsCourse par id
    @GetMapping("/champsCourse/{nomChampsCourse}")
    public Optional<ChampsCourse> getChampsCourse(@PathVariable String nomChampsCourse){
        Optional<ChampsCourse> champsCourse = champsCourseDao.findById(nomChampsCourse);
        if(!champsCourse.isPresent()){
            throw new NotFoundException("Champs de course introuvable - " + nomChampsCourse);
        }
        return champsCourse;
    }

    //mapping POST / champsCourse - add new champsCourse
    @PostMapping("/champsCourse")
    public ChampsCourse addChampsCourse(@RequestBody ChampsCourse champsCourse){
        champsCourseDao.save(champsCourse);
        return champsCourse;
    }

    //mapping PUT / champsCourse - update champsCourse
    @PutMapping("/champsCourse")
    public ChampsCourse updateChampsCourse(@RequestBody ChampsCourse champsCourse){
        champsCourseDao.save(champsCourse);
        return champsCourse;
    }

    //mapping DELETE / champsCourse - delete champsCourse
    @DeleteMapping("/champsCourse/{nomChampsCourse}")
    public String deleteChampsCourse(@PathVariable String nomChampsCourse){
        Optional<ChampsCourse> champsCourse = champsCourseDao.findById(nomChampsCourse);
        //throw exception SI null
        if(!champsCourse.isPresent()){
            throw new NotFoundException("Champs de course introuvable - " + nomChampsCourse);
        }
        champsCourseDao.deleteById(nomChampsCourse);
        return "Suppression du champs de course : " + nomChampsCourse;
    }




}
