package com.cafegory.cafegory.cafe.dto;

import com.cafegory.cafegory.cafe.domain.BusinessHour;
import com.cafegory.cafegory.cafe.domain.Cafe;
import com.cafegory.cafegory.cafe.domain.CafeTagType;
import lombok.*;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CafeSearchListResponse {

	private CafeInfo cafeInfo;
	private CafeBusinessHourInfo cafeBusinessInfo;

	public static CafeSearchListResponse from(Cafe cafe, BusinessHour businessHour) {
		CafeSearchListResponse response = new CafeSearchListResponse();
		response.cafeInfo = createCafeInfo(cafe);
		response.cafeBusinessInfo = createCafeBusinessInfo(businessHour);

		return response;
	}

	private static CafeInfo createCafeInfo(Cafe cafe) {
		return CafeInfo.builder()
			.id(cafe.getId().getId())
			.imgUrl(cafe.getImgUrl())
			.name(cafe.getName())
			.tags(cafe.getCafeTagTypes())
			.build();
	}

	private static CafeBusinessHourInfo createCafeBusinessInfo(BusinessHour businessHour) {
		return CafeBusinessHourInfo.builder()
			.openingTime(businessHour.getOpeningTme())
			.closingTime(businessHour.getClosingTme())
			.build();
	}

	@Getter
	@Setter
	@Builder
	private static class CafeInfo {

		private Long id;
		private String name;
		private String imgUrl;
		private List<CafeTagType> tags = new ArrayList<>();
	}

	@Getter
	@Setter
	@Builder
	private static class CafeBusinessHourInfo {

		private LocalTime openingTime;
		private LocalTime closingTime;
	}
}
