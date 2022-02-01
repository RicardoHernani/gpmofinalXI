package com.ricardochaves.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.ricardochaves.services.DBService;

@Configuration
@Profile("dev")
public class DevConfig {
	
	@Autowired
	private DBService dbService;
		
	@Value("${spring.jpa.hibernate.ddl-auto}")   			
	private String strategy1;	
	
	@Value("${spring.jpa.defer-datasource-initialization}")   			
	private String strategy2;	
	
	@Value("${spring.sql.init.mode}")   			
	private String strategy3;	

	
	//para criar o banco em toda inicialização deve-se usar create, true e always
	//para manter os dados deve-se usar none, false e never
		
	@Bean
	public boolean instantiateDatabase() {   
		
		if((!"create".equals(strategy1)) && (!"true".equals(strategy2)) && (!"always".equals(strategy3))) {
			return false;
		}
		
		dbService.instantiateTestDatabase();
		return true;
	}
}
