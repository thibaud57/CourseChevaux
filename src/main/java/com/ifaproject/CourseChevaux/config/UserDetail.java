package com.ifaproject.CourseChevaux.config;

import com.ifaproject.CourseChevaux.entity.Utilisateur;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserDetail implements UserDetails {
    //fields
    private String userName;
    private String password;

    private boolean active;
    private List<GrantedAuthority> authorities;

    //constructeurs
    public UserDetail() {
    }

    public UserDetail(Utilisateur utilisateur) { //utilisateur en parametre
        this.userName = utilisateur.getPseudo();
        this.password = utilisateur.getPassword();
        this.active = utilisateur.isActif();

        //définir le rôle
        this.authorities = new ArrayList<>();

        if(utilisateur.isAdmin()){
            this.authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        } else {
            this.authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        }
    }

    //implémentaiton des méthodes
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return active;
    }

    @Override
    public boolean isAccountNonLocked() {
        return active;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return active;
    }

    @Override
    public boolean isEnabled() {
        return active;
    }

}
