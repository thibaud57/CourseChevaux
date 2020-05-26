package com.ifaproject.CourseChevaux.entity;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Utilisateur {
    //fields
    @Id
    @Column(unique = true)
    private String pseudo;

    private String password;

    private String email;

    private boolean actif;

    private boolean admin;

    private String nomImageAvatar;


    //constructeurs
    public Utilisateur() {
    }

    public Utilisateur(String pseudo, String password, String email, boolean actif, boolean admin) {
        this.pseudo = pseudo;
        this.password = password;
        this.email = email;
        this.actif = actif;
        this.admin = admin;
    }

    //getters / setters
    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActif() {
        return actif;
    }

    public void setActif(boolean actif) {
        this.actif = actif;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNomImageAvatar() {
        return nomImageAvatar;
    }

    public void setNomImageAvatar(String nomImageAvatar) {
        this.nomImageAvatar = nomImageAvatar;
    }

    //tostring

    @Override
    public String toString() {
        return "Utilisateur{" +
                "pseudo='" + pseudo + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", actif=" + actif +
                ", admin=" + admin +
                '}';
    }


}
