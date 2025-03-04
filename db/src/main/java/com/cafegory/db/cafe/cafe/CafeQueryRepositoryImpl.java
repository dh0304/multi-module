package com.cafegory.db.cafe.cafe;

import com.cafegory.domain.cafe.domain.Cafe;
import com.cafegory.domain.cafe.dto.CafeSearchListRequest;
import com.cafegory.domain.cafe.repository.CafeQueryRepository;
import com.cafegory.domain.common.SliceResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CafeQueryRepositoryImpl implements CafeQueryRepository {

	private final CafeQueryDslRepository cafeQueryRepository;

	@Override
	public SliceResponse<Cafe> findCafeByRegionAndKeyword(CafeSearchListRequest request) {
		SliceResponse<CafeEntity> response = cafeQueryRepository.findCafeByRegionAndKeyword(request);

		return response.map(CafeEntity::toCafe);
	}
}
