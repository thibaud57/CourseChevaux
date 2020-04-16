package com.ifaproject.CourseChevaux.model;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity
@EntityListeners(AuditingEntityListener.class)
public class Course {
    //fields
    @Id
    private int num;

    private String designation;

    @Temporal(TemporalType.DATE)
    private Date dateCourse;

    //mappings
    @OneToMany(mappedBy = "course", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private List<Participation> participations;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "nom_champ_course")
    private ChampsCourse champsCourse;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "type_course")
    private Type type;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "nom_categorie")
    private Categorie categorie;

    //constructeurs

    public Course() {
    }

    public Course(int num, String designation, Date dateCourse) {
        this.num = num;
        this.designation = designation;
        this.dateCourse = dateCourse;
    }
//getters / setters

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public Date getDateCourse() {
        return dateCourse;
    }

    public void setDateCourse(Date dateCourse) {
        this.dateCourse = dateCourse;
    }

    public List<Participation> getParticipations() {
        return participations;
    }

    public void setParticipations(List<Participation> participations) {
        this.participations = participations;
    }

    public ChampsCourse getChampsCourse() {
        return champsCourse;
    }

    public void setChampsCourse(ChampsCourse champsCourse) {
        this.champsCourse = champsCourse;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }
//tostring

    @Override
    public String toString() {
        return "Course{" +
                "num=" + num +
                ", designation='" + designation + '\'' +
                ", dateCourse=" + dateCourse +
                '}';
    }

    //méthodes partiques
    public void addParticipation(Participation participation){
        if(participation == null){
            participations = new ArrayList<>();
        }
        participations.add(participation);
        //etablir lien bidirectionnel
        participation.setCourse(this);
    }
}
