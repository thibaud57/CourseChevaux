package com.ifaproject.CourseChevaux.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Jockey {
    //fields
    @Id
    private int matricule;

    private String nomJockey;


    //mappings
    @OneToMany(mappedBy = "jockey", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JsonIgnore
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
        if(participations == null){
            participations = new ArrayList<>();
        }
        participations.add(participation);
        //etablir lien bidirectionnel
        participation.setJockey(this);
    }
}
