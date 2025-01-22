package cafe.service;

import cafe.domain.BusinessHour;
import cafe.domain.Cafe;
import cafe.domain.CafeId;
import cafe.dto.CafeSearchListRequest;
import cafe.dto.CafeSearchListResponse;
import cafe.implement.BusinessHourReader;
import cafe.implement.CafeReader;
import common.SliceResponse;
import common.time.TimeUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CafeQueryService {

	private final CafeReader cafeReader;
	private final BusinessHourReader businessHourReader;

	private final TimeUtil timeUtil;

	public Cafe getCafe(CafeId cafeId) {
		return cafeReader.getWithTags(cafeId);
	}

	public SliceResponse<CafeSearchListResponse> searchCafesByDynamicFilter(CafeSearchListRequest request) {
		applyDefaultTimes(request);
		SliceResponse<Cafe> cafes = cafeReader.readCafes(request);

		return cafes.map(
			cafe -> {
				BusinessHour businessHour = businessHourReader.readBy(cafe.getId(), timeUtil.now().getDayOfWeek());

				return CafeSearchListResponse.from(cafe, businessHour);
			}
		);
	}

	private void applyDefaultTimes(CafeSearchListRequest request) {
		request.applyDefaultOpeningTime(timeUtil.minLocalDateTime(timeUtil.now()));
		request.applyDefaultClosingTime(timeUtil.maxLocalDateTime(timeUtil.now()));
	}
}
