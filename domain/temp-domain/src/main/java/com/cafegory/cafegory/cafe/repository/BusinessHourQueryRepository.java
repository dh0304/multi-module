package com.cafegory.cafegory.cafe.repository;

import com.cafegory.cafegory.cafe.domain.BusinessHour;
import com.cafegory.cafegory.cafe.domain.CafeId;

import java.time.DayOfWeek;
import java.util.Optional;

public interface BusinessHourQueryRepository {

	Optional<BusinessHour> findBy(CafeId cafeId, DayOfWeek dayOfWeek);
}
