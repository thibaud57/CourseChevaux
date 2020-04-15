package com.ifaproject.CourseChevaux.controller;

import com.ifaproject.CourseChevaux.dao.CourseDao;
import com.ifaproject.CourseChevaux.model.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class CourseController {
	
	CourseDao courseDao;
	
	@Autowired
	public CourseController(CourseDao courseDao) {
		this.courseDao = courseDao;
		
	}
	
	@GetMapping({"/courses","/foo"})
	public List<Course> getCourse() {
		
		return courseDao.findAll();
	} 
	
	
	
	@GetMapping("/courses/{id}")
	public Course getCourse(@PathVariable int id) {
		
	return courseDao.findById(id).orElse(null);
	} 
	
	@PutMapping("/courses")
	public Course saveCourse(@RequestBody Course course) {
		return courseDao.save(course);
	}
}
