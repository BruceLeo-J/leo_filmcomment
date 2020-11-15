package com.graduation.filmcomment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class FilmcommentApplication {

	public static void main(String[] args) {
		SpringApplication.run(FilmcommentApplication.class, args);
	}

}
