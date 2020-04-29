package com.ifaproject.CourseChevaux.dao;

import com.ifaproject.CourseChevaux.entity.Proprietaire;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProprietaireDao extends JpaRepository<Proprietaire, String> {
}
