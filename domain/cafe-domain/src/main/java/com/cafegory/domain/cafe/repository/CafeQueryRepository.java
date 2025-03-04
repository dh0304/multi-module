package com.cafegory.domain.cafe.repository;

import com.cafegory.domain.cafe.domain.Cafe;
import com.cafegory.domain.cafe.dto.CafeSearchListRequest;
import com.cafegory.domain.common.SliceResponse;

public interface CafeQueryRepository {

	SliceResponse<Cafe> findCafeByRegionAndKeyword(CafeSearchListRequest request);
}
