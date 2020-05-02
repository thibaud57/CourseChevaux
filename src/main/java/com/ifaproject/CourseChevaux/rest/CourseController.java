package com.ifaproject.CourseChevaux.rest;


import com.ifaproject.CourseChevaux.rest.exception.NotFoundException;
import com.ifaproject.CourseChevaux.dao.CourseDao;
import com.ifaproject.CourseChevaux.entity.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
//@RequestMapping("/course")
public class CourseController {

    @Autowired
    CourseDao courseDao;

    //mapping Get / courses
    @GetMapping("/course")
    public List<Course> getCourses(){
        return courseDao.findAll();
    }

    //mapping Get /  course par id
    @GetMapping("/course/{numCourse}")
    public Optional<Course> getCourse(@PathVariable int numCourse){
        Optional<Course> course = courseDao.findById(numCourse);
        if(!course.isPresent()){
            throw new NotFoundException("Course introuvable - " + numCourse);
        }
        return course;
    }

    //mapping POST / course - add new course
    @PostMapping("/course")
    public Course addCourse(@RequestBody Course course){
        courseDao.save(course);
        return course;
    }

    //mapping PUT / course - update course
    @PutMapping("/course")
    public Course updateCourse(@RequestBody Course course){
        courseDao.save(course);
        return course;
    }

    //mapping DELETE / course - delete course
    @DeleteMapping("/course/{numCourse}")
    public String deleteCourse(@PathVariable int numCourse){
        Optional<Course> course = courseDao.findById(numCourse);
        //throw exception SI null
        if(!course.isPresent()){
            throw new NotFoundException("Course introuvable - " + numCourse);
        }
        courseDao.deleteById(numCourse);
        return "Suppression de la course : " + numCourse;
    }



}
