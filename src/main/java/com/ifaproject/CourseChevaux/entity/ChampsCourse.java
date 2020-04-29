package com.ifaproject.CourseChevaux.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class ChampsCourse {
    //fields
    @Id
    private String nomChampCourse;

    //mappings
    @OneToMany(mappedBy = "champsCourse", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JsonIgnore
    private List<Course> courses;



    //constructeurs

    public ChampsCourse() {
    }

    public ChampsCourse(String nomChampCourse) {
        this.nomChampCourse = nomChampCourse;
    }

    //getters / setters

    public String getNomChampCourse() {
        return nomChampCourse;
    }

    public void setNomChampCourse(String nomChampCourse) {
        this.nomChampCourse = nomChampCourse;
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
        return "ChampsCourse{" +
                "nomChampCourse='" + nomChampCourse + '\'' +
                '}';
    }

    //ajouter une course
    public void addCourse(Course course){
        if(courses == null){
            courses = new ArrayList<>();
        }
        courses.add(course);
        //etablir lien bidirectionnel
        course.setChampsCourse(this);
    }

}
