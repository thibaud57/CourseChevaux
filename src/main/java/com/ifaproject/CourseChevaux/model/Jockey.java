package com.ifaproject.CourseChevaux.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "jockey")
public class Jockey {
    //fields
    @Id
    @Column(name = "matricule")
    private int matricule;

    @Column(name = "nom_jockey")
    private String nomJockey;


    //mappings
    @OneToMany(mappedBy = "jockey", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private List<Participation> participations;

    //constructeurs

    public Jockey() {
    }

    public Jockey(int matricule, String nomJockey) {
        this.matricule = matricule;
        this.nomJockey = nomJockey;
    }
//getters / setters

    public int getMatricule() {
        return matricule;
    }

    public void setMatricule(int matricule) {
        this.matricule = matricule;
    }

    public String getNomJockey() {
        return nomJockey;
    }

    public void setNomJockey(String nomJockey) {
        this.nomJockey = nomJockey;
    }

    public List<Participation> getParticipations() {
        return participations;
    }

    public void setParticipations(List<Participation> participations) {
        this.participations = participations;
    }
//tostring

    @Override
    public String toString() {
        return "Jockey{" +
                "matricule=" + matricule +
                ", nomJockey='" + nomJockey + '\'' +
                '}';
    }

    //méthodes partiques
    public void addParticipation(Participation participation){
        if(participation == null){
            participations = new ArrayList<>();
        }
        participations.add(participation);
        //etablir lien bidirectionnel
        participation.setJockey(this);
    }
}
