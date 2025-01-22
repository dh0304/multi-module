package com.cafegory.cafegory.cafe.implement;

import com.cafegory.cafegory.cafe.domain.Cafe;
import com.cafegory.cafegory.cafe.domain.CafeId;
import com.cafegory.cafegory.cafe.dto.CafeSearchListRequest;
import com.cafegory.cafegory.cafe.repository.CafeQueryRepository;
import com.cafegory.cafegory.cafe.repository.CafeRepository;
import com.cafegory.cafegory.common.SliceResponse;
import com.cafegory.cafegory.exception.CafegoryException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static com.cafegory.cafegory.exception.ExceptionType.CAFE_NOT_FOUND;

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
