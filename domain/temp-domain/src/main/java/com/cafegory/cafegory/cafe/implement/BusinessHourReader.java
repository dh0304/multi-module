package com.cafegory.cafegory.cafe.implement;

import com.cafegory.cafegory.cafe.domain.BusinessHour;
import com.cafegory.cafegory.cafe.domain.CafeId;
import com.cafegory.cafegory.cafe.repository.BusinessHourQueryRepository;
import com.cafegory.cafegory.exception.CafegoryException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

import static com.cafegory.cafegory.exception.ExceptionType.*;

@Component
@RequiredArgsConstructor
public class BusinessHourReader {

	private final BusinessHourQueryRepository businessHourQueryRepository;

	public BusinessHour readBy(CafeId cafeId, DayOfWeek startDate) {
		return businessHourQueryRepository.findBy(cafeId, startDate)
			.orElseThrow(() -> new CafegoryException(CAFE_BUSINESS_HOUR_NOT_FOUND));
	}
}
