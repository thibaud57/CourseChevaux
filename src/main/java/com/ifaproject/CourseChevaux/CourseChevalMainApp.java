package com.ifaproject.CourseChevaux;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class CourseChevalMainApp {

	public static void main(String[] args) {

		SpringApplication.run(CourseChevalMainApp.class, args);
	}

}
