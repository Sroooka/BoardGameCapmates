package com.capgemini.jstk.BoardGameCapmates;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;


@SpringBootApplication
@Configuration
@EnableAspectJAutoProxy
public class BoardGameCapmatesApplication {

	public static void main(String[] args) {
		SpringApplication.run(BoardGameCapmatesApplication.class, args);
		
	}
	
}
