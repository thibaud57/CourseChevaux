package com.ifaproject.CourseChevaux;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ifaproject.CourseChevaux.entity.Cheval;
import com.ifaproject.CourseChevaux.entity.Utilisateur;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;


@SpringBootTest
class CourseChevauxApplicationTests {

	@Autowired
	private  WebApplicationContext context;

	@Autowired
    private ObjectMapper mapper;

	private  MockMvc mvc;

	@BeforeEach
	public void beforeEach(){
		mvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();
	}

	@Test
    //@WithMockUser(value = "admin", roles ={"ADMIN"})
	void contextLoads() throws Exception {
	  /*  Cheval cheval = new Cheval("Gold Eagle", LocalDate.of(2018,3,14),140000,"M");
        String json = mapper.writeValueAsString(cheval);
/*
		mvc.perform(
		        put("/cheval")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json)
                    )
                .andExpect(status().isOk());*/
/*
        mvc.perform(
                get("/cheval/Gold Eagle"))
                .andExpect(MockMvcResultMatchers
                .jsonPath("$.gains")
                .value(140000)
                );
*/
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setPseudo("test2");
        utilisateur.setPassword("test2");
        String json = mapper.writeValueAsString(utilisateur);

        mvc.perform(
                put("/inscription")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk());

        String token = mvc.perform(
                post("/authentification")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        mvc.perform(
                get("/cheval")
                        .header("authorization", "Bearer " + token)
        )
                .andExpect(status().isOk());
    }

}

