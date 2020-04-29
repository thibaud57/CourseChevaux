package com.ifaproject.CourseChevaux;

import com.ifaproject.CourseChevaux.dao.*;
import com.ifaproject.CourseChevaux.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class Fixture implements ApplicationRunner {

    //Liste des champs
   private ChevalDao chevalDao;
   private ProprietaireDao proprietaireDao;
   private CourseDao courseDao;
   private JockeyDao jockeyDao;
   private ChampsCourseDao champsCourseDao;

   //Constructeur
    @Autowired
    public Fixture(ChevalDao chevalDao, ProprietaireDao proprietaireDao, CourseDao courseDao, JockeyDao jockeyDao, ChampsCourseDao champsCourseDao) {
        this.chevalDao = chevalDao;
        this.proprietaireDao = proprietaireDao;
        this.courseDao = courseDao;
        this.jockeyDao = jockeyDao;
        this.champsCourseDao = champsCourseDao;
    }

//Application test
    @Override
    public void run(ApplicationArguments args) throws Exception {

        //Ajout d'un cheval
        Cheval cheval = new Cheval("Marie Poppins", LocalDate.of(2015,2,1),72030, "F");

        //Ajout d'un proprietaire
        Proprietaire proprietaire = new Proprietaire("Bernard Tapie");
        proprietaireDao.save(proprietaire);
        //Ajouter le cheval à son proprietaire
        cheval.addProprietaire(proprietaire);

        //Sauvegarder le cheval
        chevalDao.save(cheval);


        //Ajouter un jockey
        Jockey jockey = new Jockey(70, "Hector Oaks");
        jockeyDao.save(jockey);


        //Ajouter un champs de course
        ChampsCourse champsCourse = new ChampsCourse("Longchamps");

        //Ajouter une course
        Course course = new Course(71, "R1: Prix des canadiens", LocalDate.of(2020,1,14), "2020-2021",99045, TypeEnum.Tiercé, TypeCategorie.Trot_attelé);

        //Indiquer une participation
        Participation participation = new Participation(6, 5);
        //Ajouter à la participation
        cheval.addParticipation(participation); //un cheval
        jockey.addParticipation(participation); //un jockey
        course.addParticipation(participation); //une course

        //sauvegarder la course
        //courseDao.save(course);

        //sauvegarder le champs de course (+ ajouter la course)
        champsCourse.addCourse(course);
        champsCourseDao.save(champsCourse);

    }
}
