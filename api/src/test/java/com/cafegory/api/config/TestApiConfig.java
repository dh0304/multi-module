package com.cafegory.api.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@EnableAutoConfiguration
@ComponentScan(basePackages = {
        "com.cafegory.api",
        "com.cafegory.domain.study",
        "com.cafegory.domain.member",
        "com.cafegory.domain.cafe",
        "com.cafegory.domain.qna",
        "com.cafegory.domain.common",
        "com.cafegory.db",
        "com.cafegory.auth"
})
public class TestApiConfig {
}
