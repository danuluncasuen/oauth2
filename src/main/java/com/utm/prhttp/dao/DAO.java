package com.utm.prhttp.dao;

import com.utm.prhttp.model.User;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@Configuration
@ComponentScan({"com.utm.prhttp"})
@EntityScan(basePackages = "com.utm.prhttp.model")
@EnableJpaRepositories(basePackages = "com.utm.prhttp")
public interface DAO extends JpaRepository<User, Integer> {

    User findByUsername(String username);

}
