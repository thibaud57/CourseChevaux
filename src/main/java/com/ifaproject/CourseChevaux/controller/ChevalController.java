package com.ifaproject.CourseChevaux.controller;


import com.ifaproject.CourseChevaux.dao.ChevalDao;
import com.ifaproject.CourseChevaux.model.Cheval;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class ChevalController {
	
	ChevalDao chevalDao;
	
	@Autowired
	public ChevalController(ChevalDao chevalDao) {
		this.chevalDao =chevalDao ;
		
	}
	
	@GetMapping({"/chevaux","/foo"})
	public List<Cheval> getCheval() {
		
		return chevalDao.findAll();
	} 
	
	
	
	@GetMapping("/chevaux/{id}")
	public Cheval getCheval(@PathVariable int id) {
		
	return chevalDao.findById(id).orElse(null);
	} 
	
	@PutMapping("/chevaux")
	public Cheval saveCourse(@RequestBody Cheval cheval) {
		return chevalDao.save(cheval);
	}
}
