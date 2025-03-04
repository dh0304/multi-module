package com.cafegory.domain.cafe.repository;

import com.cafegory.domain.cafe.domain.BusinessHour;
import com.cafegory.domain.cafe.domain.CafeId;

import java.time.DayOfWeek;
import java.util.Optional;

public interface BusinessHourQueryRepository {

	Optional<BusinessHour> findBy(CafeId cafeId, DayOfWeek dayOfWeek);
}
