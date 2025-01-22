package cafe.repository;

import cafe.domain.Cafe;
import cafe.dto.CafeSearchListRequest;
import common.SliceResponse;

public interface CafeQueryRepository {

	SliceResponse<Cafe> findCafeByRegionAndKeyword(CafeSearchListRequest request);
}
