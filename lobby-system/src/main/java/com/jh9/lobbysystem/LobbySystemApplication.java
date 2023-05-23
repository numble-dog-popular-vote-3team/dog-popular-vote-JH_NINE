package com.jh9.lobbysystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@EnableReactiveMongoRepositories
@SpringBootApplication
public class LobbySystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(LobbySystemApplication.class, args);
	}

}
