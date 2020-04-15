package com.ifaproject.CourseChevaux.dao;

import com.ifaproject.CourseChevaux.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseDao extends JpaRepository<Course, Integer> {
	
	

}
