package com.cafegory.cafegory.cafe.repository;

import com.cafegory.cafegory.cafe.domain.Cafe;
import com.cafegory.cafegory.cafe.dto.CafeSearchListRequest;
import com.cafegory.cafegory.common.SliceResponse;

public interface CafeQueryRepository {

	SliceResponse<Cafe> findCafeByRegionAndKeyword(CafeSearchListRequest request);
}
