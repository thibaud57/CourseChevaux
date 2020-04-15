package com.ifaproject.CourseChevaux.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "type")
public class Type {
    //fields
    @Id
    @Column(name = "type_course")
    private String typeCourse;

    //mappings
    @OneToMany(mappedBy = "type", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private List<Course> courses;

    //constructeurs

    public Type() {
    }

    public Type(String typeCourse) {
        this.typeCourse = typeCourse;
    }
//getters / setters

    public String getTypeCourse() {
        return typeCourse;
    }

    public void setTypeCourse(String typeCourse) {
        this.typeCourse = typeCourse;
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
        return "Type{" +
                "typeCourse='" + typeCourse + '\'' +
                '}';
    }

    //ajouter une course
    public void addCourse(Course course){
        if(course == null){
            courses = new ArrayList<>();
        }
        courses.add(course);
        //etablir lien bidirectionnel
        course.setType(this);
    }
}
