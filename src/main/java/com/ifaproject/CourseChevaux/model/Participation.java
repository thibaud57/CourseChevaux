package com.ifaproject.CourseChevaux.model;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Participation {
    //fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int position;

    //mappings
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "nom_cheval")
    private Cheval cheval;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "matricule")
    private Jockey jockey;


    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "num")
    private Course course;
    //constructeurs

    public Participation() {
    }

    public Participation(int position) {
        this.position = position;
    }
//getters / setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public Cheval getCheval() {
        return cheval;
    }

    public void setCheval(Cheval cheval) {
        this.cheval = cheval;
    }

    public Jockey getJockey() {
        return jockey;
    }

    public void setJockey(Jockey jockey) {
        this.jockey = jockey;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    //tostring
    @Override
    public String toString() {
        return "Participation{" +
                "id=" + id +
                ", position=" + position +
                '}';
    }
}
