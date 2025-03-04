package com.cafegory.domain.cafe.testfixtures.builder;

import com.cafegory.domain.cafe.domain.BusinessHour;
import com.cafegory.domain.cafe.domain.BusinessHourId;
import com.cafegory.domain.common.time.FakeTimeUtil;

import java.time.DayOfWeek;
import java.time.LocalTime;

public class BusinessHourBuilder {

	private BusinessHourId id = new BusinessHourId(1L);
	private DayOfWeek dayOfWeek = DayOfWeek.MONDAY;
	private LocalTime openingTime = LocalTime.of(9, 0);
	private LocalTime closingTime = LocalTime.of(17, 0);

	private BusinessHourBuilder() {
	}

	private BusinessHourBuilder(BusinessHourBuilder copy) {
		this.id = copy.id;
		this.dayOfWeek = copy.dayOfWeek;
		this.openingTime = copy.openingTime;
		this.closingTime = copy.closingTime;
	}

	public BusinessHourBuilder but() {
		return new BusinessHourBuilder(this);
	}

	public static BusinessHourBuilder aBusinessHour() {
		return new BusinessHourBuilder();
	}

	public BusinessHourBuilder withId(Long id) {
		this.id = new BusinessHourId(id);
		return this;
	}

	public BusinessHourBuilder withDayOfWeek(DayOfWeek dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
		return this;
	}

	public BusinessHourBuilder withOpeningStartOfDay() {
		this.openingTime = FakeTimeUtil.localTimeStatic(0, 0, 0);
		return this;
	}

	public BusinessHourBuilder withOpeningTime(int hour, int minute) {
		this.openingTime = LocalTime.of(hour, minute);
		return this;
	}

	public BusinessHourBuilder withClosingTime(int hour, int minute) {
		this.closingTime = LocalTime.of(hour, minute);
		return this;
	}

	public BusinessHourBuilder withClosingEndOfDay() {
		this.closingTime = FakeTimeUtil.maxLocalTimeStatic();
		return this;
	}

	public BusinessHour build() {
		return BusinessHour.builder()
			.id(this.id)
			.dayOfWeek(this.dayOfWeek)
			.openingTme(this.openingTime)
			.closingTme(this.closingTime)
			.build();
	}
}


