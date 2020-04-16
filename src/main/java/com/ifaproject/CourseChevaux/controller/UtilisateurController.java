package com.ifaproject.CourseChevaux.controller;

import com.ifaproject.CourseChevaux.model.Utilisateur;
import com.ifaproject.CourseChevaux.securite.JwtUtil;
import com.ifaproject.CourseChevaux.securite.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UtilisateurController {
    private AuthenticationManager authenticationManager;
    private UserDetailService userDetailService;
    private JwtUtil jwtUtil;

    @Autowired
    public UtilisateurController(
            AuthenticationManager authenticationManager,
            UserDetailService userDetailService,
            JwtUtil jwtUtil) {
        this.userDetailService = userDetailService;
        this.jwtUtil = jwtUtil;
    }
    @PostMapping("/authentification")
    public ResponseEntity<String> authentification(@RequestBody Utilisateur utilisateur) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            utilisateur.getPseudo(), utilisateur.getPassword()));
        }
        catch (BadCredentialsException e) {
            throw new Exception("Pseudo ou mot de passe incorrect", e);
        }
        final UserDetails userDetail = userDetailService.loadUserByUsername(utilisateur.getPseudo());
        return ResponseEntity.ok(jwtUtil.generateToken(userDetail));
    }

}
