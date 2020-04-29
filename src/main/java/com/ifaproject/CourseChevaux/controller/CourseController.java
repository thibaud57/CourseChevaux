package com.ifaproject.CourseChevaux.controller;

import com.ifaproject.CourseChevaux.dao.CourseDao;
import com.ifaproject.CourseChevaux.entity.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/course")
public class CourseController {

    @Autowired
    CourseDao courseDao;

    //Créer une course
    @PutMapping("/cree")
    public void saveCourse(@RequestBody Course course){
        courseDao.save(course);
    }




}
