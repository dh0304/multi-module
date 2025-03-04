package com.cafegory.domain.study.implement;

import com.cafegory.domain.cafe.domain.BusinessHour;
import com.cafegory.domain.cafe.implement.BusinessHourOpenChecker;
import com.cafegory.domain.common.exception.CafegoryException;
import com.cafegory.domain.study.domain.Schedule;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static com.cafegory.domain.common.exception.ExceptionType.STUDY_ONCE_CREATE_BETWEEN_CAFE_BUSINESS_HOURS;

@Component
@RequiredArgsConstructor
public class ScheduleValidator {

	private final BusinessHourOpenChecker openChecker;

	public void validateBetweenBusinessHour(Schedule schedule, BusinessHour businessHour) {
		boolean isBetweenBusinessHour = openChecker.checkBetweenBusinessHours(
			businessHour.getOpeningTme(), businessHour.getClosingTme(),
			schedule.getStartDateTime().toLocalTime(), schedule.getEndDateTime().toLocalTime());

		if (!isBetweenBusinessHour) {
			throw new CafegoryException(STUDY_ONCE_CREATE_BETWEEN_CAFE_BUSINESS_HOURS);
		}
	}
}
