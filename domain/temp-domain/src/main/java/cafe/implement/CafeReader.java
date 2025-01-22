package cafe.implement;

import cafe.domain.Cafe;
import cafe.domain.CafeId;
import cafe.dto.CafeSearchListRequest;
import cafe.repository.CafeQueryRepository;
import cafe.repository.CafeRepository;
import common.SliceResponse;
import exception.CafegoryException;
import exception.ExceptionType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static exception.ExceptionType.CAFE_NOT_FOUND;

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
