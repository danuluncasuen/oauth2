package com.utm.prhttp;

import com.utm.prhttp.dao.DAO;
import com.utm.prhttp.model.LocalUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class PrHttpApplication  {

	public static void main(String[] args) {
		SpringApplication.run(PrHttpApplication.class, args);

	}

	@Autowired
	public void authenticationManager(AuthenticationManagerBuilder builder, DAO dao) throws Exception {
		builder.userDetailsService(s -> new LocalUserDetails(dao.findByUsername(s)));
	}

	@Bean
	public PasswordEncoder getPasswordEncoder(){
		return new BCryptPasswordEncoder();
	}

}
