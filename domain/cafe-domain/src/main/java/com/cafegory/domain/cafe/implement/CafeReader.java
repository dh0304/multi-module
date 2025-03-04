package com.cafegory.domain.cafe.implement;

import com.cafegory.domain.cafe.domain.Cafe;
import com.cafegory.domain.cafe.domain.CafeId;
import com.cafegory.domain.cafe.dto.CafeSearchListRequest;
import com.cafegory.domain.common.SliceResponse;
import com.cafegory.domain.cafe.repository.CafeQueryRepository;
import com.cafegory.domain.cafe.repository.CafeRepository;
import com.cafegory.domain.common.exception.CafegoryException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static com.cafegory.domain.common.exception.ExceptionType.CAFE_NOT_FOUND;

@Component
@RequiredArgsConstructor
public class CafeReader {

	private final CafeRepository cafeRepository;
	private final CafeQueryRepository cafeQueryRepository;

	public Cafe read(CafeId cafeId) {
		return cafeRepository.findById(cafeId)
			.orElseThrow(() -> new CafegoryException(CAFE_NOT_FOUND));
	}

	public Cafe getWithTags(CafeId cafeId) {
		return cafeRepository.findWithTags(cafeId)
			.orElseThrow(() -> new CafegoryException(CAFE_NOT_FOUND));
	}

	public SliceResponse<Cafe> readCafes(CafeSearchListRequest request) {
		return cafeQueryRepository.findCafeByRegionAndKeyword(request);
	}
}
