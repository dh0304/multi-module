package com.cafegory.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {
		"com.cafegory.api",
		"com.cafegory.domain.study",
		"com.cafegory.domain.member",
		"com.cafegory.domain.cafe",
		"com.cafegory.domain.qna",
		"com.cafegory.domain.common",
		"com.cafegory.db",
		"com.cafegory.auth"
})
public class CafegoryBackendRestApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(CafegoryBackendRestApiApplication.class, args);
	}

}
