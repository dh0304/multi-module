package com.cafegory.db.config;

import com.cafegory.domain.common.time.TimeUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.auditing.DateTimeProvider;

import java.time.temporal.TemporalAccessor;
import java.util.Optional;

@RequiredArgsConstructor
public class CurrentDateTimeProvider implements DateTimeProvider {

	private final TimeUtil timeUtil;

	@Override
	public Optional<TemporalAccessor> getNow() {
		return Optional.of(timeUtil.now());
	}
}
