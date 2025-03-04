package com.cafegory.api.cafe;

import com.cafegory.domain.cafe.domain.BusinessHour;
import com.cafegory.domain.cafe.domain.Cafe;
import com.cafegory.domain.cafe.domain.CafeId;
import com.cafegory.domain.cafe.dto.CafeSearchListRequest;
import com.cafegory.domain.cafe.dto.CafeSearchListResponse;
import com.cafegory.domain.cafe.service.BusinessHourService;
import com.cafegory.domain.cafe.service.CafeQueryService;
import com.cafegory.domain.common.SliceResponse;
import com.cafegory.domain.common.time.TimeUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cafes")
public class CafeController {

	private final CafeQueryService cafeQueryService;
	private final BusinessHourService businessHourService;
	private final TimeUtil timeUtil;

	@GetMapping("/{cafeId}")
	public ResponseEntity<CafeDetailResponse> getCafeDetail(@PathVariable Long cafeId) {
		Cafe cafe = cafeQueryService.getCafe(new CafeId(cafeId));
		BusinessHour businessHour = businessHourService.findBusinessHour(cafe.getId(), timeUtil.now());
		boolean isOpen = businessHourService.isOpen(businessHour, timeUtil.now());

		CafeDetailResponse response = CafeDetailResponse.of(cafe, businessHour, isOpen);
		return ResponseEntity.ok(response);
	}

	@GetMapping
	public ResponseEntity<SliceResponse<CafeSearchListResponse>> getCafes(
		@Validated @ModelAttribute CafeSearchListRequest request
	) {
		SliceResponse<CafeSearchListResponse> response = cafeQueryService.searchCafesByDynamicFilter(request);
		return ResponseEntity.ok(response);
	}
}
