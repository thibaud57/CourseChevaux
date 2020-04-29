package com.ifaproject.CourseChevaux.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Proprietaire {
    //fields
    @Id
    private String nomProprio;

    //mappings
    @ManyToMany(mappedBy = "proprietaires", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JsonIgnore
    private List<Cheval> chevaux;


    //constructeurs

    public Proprietaire() {
    }

    public Proprietaire(String nomProprio) {
        this.nomProprio = nomProprio;
    }
//getters / setters

    public String getNomProprio() {
        return nomProprio;
    }

    public void setNomProprio(String nomProprio) {
        this.nomProprio = nomProprio;
    }

    public List<Cheval> getChevaux() {
        return chevaux;
    }

    public void setChevaux(List<Cheval> chevaux) {
        this.chevaux = chevaux;
    }


//tostring

    @Override
    public String toString() {
        return "Propriétaire{" +
                "nomProprio='" + nomProprio + '\'' +
                '}';
    }

}
