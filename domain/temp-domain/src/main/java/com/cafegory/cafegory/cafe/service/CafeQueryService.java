package com.cafegory.cafegory.cafe.service;

import com.cafegory.cafegory.cafe.domain.BusinessHour;
import com.cafegory.cafegory.cafe.domain.Cafe;
import com.cafegory.cafegory.cafe.domain.CafeId;
import com.cafegory.cafegory.cafe.dto.CafeSearchListRequest;
import com.cafegory.cafegory.cafe.dto.CafeSearchListResponse;
import com.cafegory.cafegory.cafe.implement.BusinessHourReader;
import com.cafegory.cafegory.cafe.implement.CafeReader;
import com.cafegory.cafegory.common.SliceResponse;
import com.cafegory.cafegory.common.time.TimeUtil;
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
