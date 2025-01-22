package cafe.implement;

import cafe.domain.BusinessHour;
import cafe.domain.CafeId;
import cafe.repository.BusinessHourQueryRepository;
import exception.CafegoryException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

import static exception.ExceptionType.*;

@Component
@RequiredArgsConstructor
public class BusinessHourReader {

	private final BusinessHourQueryRepository businessHourQueryRepository;

	public BusinessHour readBy(CafeId cafeId, DayOfWeek startDate) {
		return businessHourQueryRepository.findBy(cafeId, startDate)
			.orElseThrow(() -> new CafegoryException(CAFE_BUSINESS_HOUR_NOT_FOUND));
	}
}
