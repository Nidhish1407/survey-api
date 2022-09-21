package com.nidhish.surveyapi.surveyapi.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsCommandLineRunner implements CommandLineRunner {

	private Logger logger = LoggerFactory.getLogger(getClass());
	private UserDetailsRspository repository;
	
	

	public UserDetailsCommandLineRunner(UserDetailsRspository repository) {
		super();
		this.repository = repository;
	}



	@Override
	public void run(String... args) throws Exception {
		repository.save(new UserDetails("Nidhish", "Admin"));
		repository.save(new UserDetails("Ravi", "Admin"));
		repository.save(new UserDetails("Raj", "Admin"));
	}

}
