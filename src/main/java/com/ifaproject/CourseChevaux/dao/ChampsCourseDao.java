package com.ifaproject.CourseChevaux.dao;

import com.ifaproject.CourseChevaux.entity.ChampsCourse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChampsCourseDao extends JpaRepository<ChampsCourse, String > {
}
