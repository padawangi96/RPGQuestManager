package com.padawangi.rpgquestmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
//@EnableElasticsearchRepositories("com.padawangi.rpgquestmanager.repository")
//@EnableJpaRepositories("com.padawangi.rpgquestmanager.jpa")
public class RpgQuestManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(RpgQuestManagerApplication.class, args);
	}

}
