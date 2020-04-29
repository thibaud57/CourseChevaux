package com.ifaproject.CourseChevaux.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDate;
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

    private LocalDate dateCourse;

    private String saison;

    private int dotation;

    //mappings
    @OneToMany(mappedBy = "course", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JsonIgnore
    private List<Participation> participations;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "nom_champ_course")
    private ChampsCourse champsCourse;


    @Enumerated(EnumType.STRING)
    private TypeEnum typeEnum;

    @Enumerated(EnumType.STRING)
    private TypeCategorie typeCategorie;

    //constructeurs

    public Course() {
    }

    public Course(int num, String designation, LocalDate dateCourse, String saison, int dotation, TypeEnum typeEnum, TypeCategorie typeCategorie) {
        this.num = num;
        this.designation = designation;
        this.dateCourse = dateCourse;
        this.saison = saison;
        this.dotation = dotation;
        this.typeEnum = typeEnum;
        this.typeCategorie = typeCategorie;
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

    public LocalDate getDateCourse() {
        return dateCourse;
    }

    public void setDateCourse(LocalDate dateCourse) {
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

    public TypeEnum getTypeEnum() {
        return typeEnum;
    }

    public void setTypeEnum(TypeEnum typeEnum) {
        this.typeEnum = typeEnum;
    }

    public TypeCategorie getTypeCategorie() {
        return typeCategorie;
    }

    public void setTypeCategorie(TypeCategorie typeCategorie) {
        this.typeCategorie = typeCategorie;
    }

    public String getSaison() {
        return saison;
    }

    public void setSaison(String saison) {
        this.saison = saison;
    }

    public int getDotation() {
        return dotation;
    }

    public void setDotation(int dotation) {
        this.dotation = dotation;
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

    //méthodes pratiques
    public void addParticipation(Participation participation){
        if(participations == null){
            participations = new ArrayList<>();
        }
        participations.add(participation);
        //etablir lien bidirectionnel
        participation.setCourse(this);
    }


}
