package com.ifaproject.CourseChevaux.rest;

import com.ifaproject.CourseChevaux.dao.UtilisateurDao;
import com.ifaproject.CourseChevaux.entity.Utilisateur;
import com.ifaproject.CourseChevaux.config.JwtUtil;
import com.ifaproject.CourseChevaux.config.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin
public class UtilisateurController {
    private AuthenticationManager authenticationManager;
    private UserDetailService userDetailService;
    private JwtUtil jwtUtil;
    private UtilisateurDao utilisateurDao;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UtilisateurController(AuthenticationManager authenticationManager, UserDetailService userDetailService, JwtUtil jwtUtil, UtilisateurDao utilisateurDao, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.userDetailService = userDetailService;
        this.jwtUtil = jwtUtil;
        this.utilisateurDao = utilisateurDao;
        this.passwordEncoder = passwordEncoder;
    }

    @PutMapping("/inscription")
    public String inscription(@RequestBody Utilisateur utilisateur){
        utilisateur.setActif(true);
        //crypter le pass
        utilisateur.setPassword(passwordEncoder.encode(utilisateur.getPassword()));
        //sauvegarder l'utilisateur
        utilisateurDao.save(utilisateur);
        return utilisateur.getPseudo();
    }

    @PostMapping("/authentification")
    public ResponseEntity<String> authentification(@RequestBody Utilisateur utilisateur) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            utilisateur.getPseudo(), utilisateur.getPassword()));
        } catch (BadCredentialsException e) {
            throw new Exception("Pseudo ou mot de passe incorrect", e);
        }
        final UserDetails userDetail = userDetailService.loadUserByUsername(utilisateur.getPseudo());
        return ResponseEntity.ok(jwtUtil.generateToken(userDetail));
    }

    @GetMapping("/utilisateurs")
    public List<Utilisateur> getListeUtilisateur() {
        return utilisateurDao.findAll();
    }

    @GetMapping("/utilisateur/{pseudo}")
    public Utilisateur getUtilisateurPseudo(@PathVariable String pseudo) throws Exception {
        return utilisateurDao.findByPseudo(pseudo).orElse(null);
    }

    @PutMapping("/utilisateur")
    public boolean saveUtilisateur(@RequestBody Utilisateur utilisateur) throws Exception {

        Utilisateur tempsUtilisateur = utilisateurDao.findByPseudo(utilisateur.getPseudo()).orElseThrow(() -> new Exception("utilisateur non trouvé"));

        tempsUtilisateur.setEmail(utilisateur.getEmail());

        utilisateurDao.save(tempsUtilisateur);

        return true;

    }

    @GetMapping(value = "/utilisateur/{pseudo}/photo", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> getImage(@PathVariable String pseudo) throws IOException {

        Utilisateur utilisateur = utilisateurDao.findByPseudo(pseudo).orElse(null);

        ClassPathResource imgFile = new ClassPathResource("private/images/avatars/" + utilisateur.getNomImageAvatar());
        byte[] bytes = StreamUtils.copyToByteArray(imgFile.getInputStream());

        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_PNG)
                .body(bytes);
    }
}
