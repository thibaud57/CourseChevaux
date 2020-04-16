package com.ifaproject.CourseChevaux.model;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.List;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Categorie {
    //fields
    @Id
    private String nomCategorie;

//mappings
@OneToMany(mappedBy = "categorie", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
private List<Course> courses;

    //constructeurs

    public Categorie() {
    }

    public Categorie(String nomCategorie) {
        this.nomCategorie = nomCategorie;
    }
//getters / setters

    public String getNomCategorie() {
        return nomCategorie;
    }

    public void setNomCategorie(String nomCategorie) {
        this.nomCategorie = nomCategorie;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }
//tostring

    @Override
    public String toString() {
        return "Catégorie{" +
                "nomCategorie='" + nomCategorie + '\'' +
                '}';
    }
}
