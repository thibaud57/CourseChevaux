package com.ifaproject.CourseChevaux.dao;

import com.ifaproject.CourseChevaux.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UtilisateurDao extends JpaRepository<Utilisateur, String> {
    Optional<Utilisateur> findByPseudo(String pseudo);

}
