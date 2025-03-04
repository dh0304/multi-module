package com.cafegory.db.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan(basePackages = "com.cafegory.db")
@EnableJpaRepositories(basePackages = "com.cafegory.db")
public class JpaConfig {
}
