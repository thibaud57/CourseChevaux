package com.ifaproject.CourseChevaux.config;

import com.ifaproject.CourseChevaux.dao.UtilisateurDao;
import com.ifaproject.CourseChevaux.entity.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailService implements UserDetailsService {
    @Autowired
    UtilisateurDao utilisateurDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Utilisateur utilisateur = utilisateurDao.findByPseudo(username)
                .orElseThrow(() -> new UsernameNotFoundException("Inconnu: " + username));
        return new UserDetail(utilisateur);
    }
}
