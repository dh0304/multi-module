package com.cafegory.domain.cafe.domain;

import lombok.Builder;
import lombok.Getter;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Builder
public class BusinessHour {

	private BusinessHourId id;
	private DayOfWeek dayOfWeek;
	private LocalTime openingTme;
	private LocalTime closingTme;

	public boolean hasId(BusinessHourId id) {
		return this.id.isSameId(id);
	}

	public boolean existsMatchingDayOfWeek(LocalDateTime now) {
		try {
			return now.getDayOfWeek().equals(this.dayOfWeek);
		} catch (IllegalArgumentException e) {
			return false;
		}
	}
}
