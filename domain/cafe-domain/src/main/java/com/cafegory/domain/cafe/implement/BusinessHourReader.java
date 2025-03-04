package com.cafegory.domain.cafe.implement;

import com.cafegory.domain.cafe.domain.BusinessHour;
import com.cafegory.domain.cafe.domain.CafeId;
import com.cafegory.domain.cafe.repository.BusinessHourQueryRepository;
import com.cafegory.domain.common.exception.CafegoryException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

import static com.cafegory.domain.common.exception.ExceptionType.CAFE_BUSINESS_HOUR_NOT_FOUND;

@Component
@RequiredArgsConstructor
public class BusinessHourReader {

	private final BusinessHourQueryRepository businessHourQueryRepository;

	public BusinessHour readBy(CafeId cafeId, DayOfWeek startDate) {
		return businessHourQueryRepository.findBy(cafeId, startDate)
			.orElseThrow(() -> new CafegoryException(CAFE_BUSINESS_HOUR_NOT_FOUND));
	}
}
