package cafe.implement;

import cafe.domain.BusinessHour;
import exception.CafegoryException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import study.domain.Schedule;

import static exception.ExceptionType.*;

@Component
@RequiredArgsConstructor
public class BusinessHourValidator {

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
