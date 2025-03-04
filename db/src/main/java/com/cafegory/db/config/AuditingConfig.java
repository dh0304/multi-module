package com.cafegory.db.config;

import com.cafegory.domain.common.time.TimeUtil;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing(dateTimeProviderRef = "currentDateTimeProvider")
@ConditionalOnProperty(name = "spring.profiles.active", havingValue = "!test")
public class AuditingConfig {

	@Bean
	public CurrentDateTimeProvider currentDateTimeProvider(TimeUtil timeUtil) {
		return new CurrentDateTimeProvider(timeUtil);
	}
}
