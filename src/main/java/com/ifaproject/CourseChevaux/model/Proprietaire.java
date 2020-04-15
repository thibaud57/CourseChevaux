package com.ifaproject.CourseChevaux.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "proprietaire")
public class Proprietaire {
    //fields
    @Id
    @Column(name = "nom_proprio")
    private String nomProprio;

    //mappings
    @ManyToMany(mappedBy = "proprietaires", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
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
