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
public class Cheval{
    //fields
    @Id
    private String nomCheval;

    private LocalDate dateNais;

    private int gains;

    private String sexe;

    //mappings
    @OneToMany(mappedBy = "cheval", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JsonIgnore
    private List<Participation> participations;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(
            name = "appartient",
            joinColumns = @JoinColumn(name = "nom_cheval"),
            inverseJoinColumns = @JoinColumn(name = "nom_proprio")
    )
    @JsonIgnore
    private List<Proprietaire> proprietaires;

    /*
    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(
            name = "parent",
            joinColumns = @JoinColumn(name = "nom_cheval_parent"),
            inverseJoinColumns = @JoinColumn(name = "nom_cheval_enfant")
    )
    private List<Cheval> chevalParent;

    @ManyToMany(mappedBy = "chevalParent", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private List<Cheval> chevalEnfant;
*/


    //constructeurs

    public Cheval() {
    }

    public Cheval(String nomCheval, LocalDate dateNais, int gains, String sexe) {
        this.nomCheval = nomCheval;
        this.dateNais = dateNais;
        this.gains = gains;
        this.sexe = sexe;
    }

//getters / setters

    public String getNomCheval() {
        return nomCheval;
    }

    public void setNomCheval(String nomCheval) {
        this.nomCheval = nomCheval;
    }

    public List<Proprietaire> getProprietaires() {
        return proprietaires;
    }

    public void setProprietaires(List<Proprietaire> proprietaires) {
        this.proprietaires = proprietaires;
    }

    public LocalDate getDateNais() {
        return dateNais;
    }

    public void setDateNais(LocalDate dateNais) {
        this.dateNais = dateNais;
    }

    public int getGains() {
        return gains;
    }

    public void setGains(int gains) {
        this.gains = gains;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public List<Participation> getParticipations() {
        return participations;
    }

    public void setParticipations(List<Participation> participations) {
        this.participations = participations;
    }
/*
    public List<Cheval> getChevalParent() {
        return chevalParent;
    }

    public void setChevalParent(List<Cheval> chevalParent) {
        this.chevalParent = chevalParent;
    }

    public List<Cheval> getChevalEnfant() {
        return chevalEnfant;
    }

    public void setChevalEnfant(List<Cheval> chevalEnfant) {
        this.chevalEnfant = chevalEnfant;
    }
*/
//tostring


    @Override
    public String toString() {
        return "Cheval{" +
                "nomCheval='" + nomCheval + '\'' +
                ", dateNais=" + dateNais +
                ", gains=" + gains +
                ", sexe='" + sexe + '\'' +
                '}';
    }

    //méthodes partiques
    public void addParticipation(Participation participation){
       if(participations == null){
           participations = new ArrayList<>();
       }
       participations.add(participation);
       //etablir lien bidirectionnel
       participation.setCheval(this);
    }

    public void addProprietaire(Proprietaire proprietaire){
        if(proprietaires == null){
            proprietaires = new ArrayList<>();
        }
        proprietaires.add(proprietaire);
    }

}
