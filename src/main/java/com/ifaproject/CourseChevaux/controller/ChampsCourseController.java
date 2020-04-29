package com.ifaproject.CourseChevaux.controller;

import com.ifaproject.CourseChevaux.dao.ChampsCourseDao;
import com.ifaproject.CourseChevaux.entity.ChampsCourse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/champs-de-course")
public class ChampsCourseController {

    @Autowired
    ChampsCourseDao champsCourseDao;

    //Créer une course
    @PutMapping("/cree")
    public void saveChampsCourse(@RequestBody ChampsCourse champsCourse){
        champsCourseDao.save(champsCourse);
    }




}
