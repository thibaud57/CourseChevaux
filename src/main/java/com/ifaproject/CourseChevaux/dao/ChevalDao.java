package com.ifaproject.CourseChevaux.dao;

import com.ifaproject.CourseChevaux.entity.Cheval;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChevalDao extends JpaRepository<Cheval, String> {
}
