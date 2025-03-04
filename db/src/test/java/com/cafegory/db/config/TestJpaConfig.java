package com.cafegory.db.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan(basePackages = "com.cafegory.db")
@EnableJpaRepositories(basePackages = "com.cafegory.db")
public class TestJpaConfig {
}
