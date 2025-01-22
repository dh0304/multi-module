package cafe.repository;

import cafe.domain.BusinessHour;
import cafe.domain.CafeId;

import java.time.DayOfWeek;
import java.util.Optional;

public interface BusinessHourQueryRepository {

	Optional<BusinessHour> findBy(CafeId cafeId, DayOfWeek dayOfWeek);
}
