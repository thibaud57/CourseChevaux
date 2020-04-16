package com.ifaproject.CourseChevaux.dao;

import com.ifaproject.CourseChevaux.model.Jockey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JockeyDao extends JpaRepository<Jockey, Integer> {

}
