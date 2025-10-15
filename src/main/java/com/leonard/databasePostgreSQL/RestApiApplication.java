package com.leonard.databasePostgreSQL;

import lombok.extern.java.Log;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Log
public class RestApiApplication {



	public static void main(String[] args) {
		SpringApplication.run(RestApiApplication.class, args);
	}

}
