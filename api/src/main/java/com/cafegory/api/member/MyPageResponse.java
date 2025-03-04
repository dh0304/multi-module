package com.cafegory.api.member;

import com.cafegory.domain.cafe.domain.Cafe;
import com.cafegory.domain.cafe.domain.CafeTagType;
import com.cafegory.domain.member.domain.BeverageSize;
import com.cafegory.domain.member.domain.Member;
import com.cafegory.domain.study.domain.Review;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MyPageResponse {

	private MyInfo myInfo;
	private List<ReviewInfo> reviewsInfo;

	public static MyPageResponse of(Member member, List<Review> reviews) {
		MyPageResponse response = new MyPageResponse();

		response.myInfo = createMyInfo(member);
		response.reviewsInfo = createReviewsInfo(reviews);

		return response;
	}

	private static List<ReviewInfo> createReviewsInfo(List<Review> reviews) {
		return reviews.stream()
			.map(MyPageResponse::createReviewInfo)
			.collect(Collectors.toList());
	}

	private static ReviewInfo createReviewInfo(Review review) {
		Cafe cafe = review.getCafe();

		return ReviewInfo.builder()
			.tags(review.getTags())
			.cafeInfo(
				ReviewInfo.CafeInfo.builder()
					.id(cafe.getId().getId())
					.imgUrl(cafe.getImgUrl())
					.name(cafe.getName())
					.build()
			)
			.build();
	}

	private static MyInfo createMyInfo(Member member) {
		return MyInfo.builder()
			.nickname(member.getContent().getNickname())
			.email(member.getEmail())
			.profileUrl(member.getContent().getImgUrl())
			.bio(member.getBio())
			.beverageSize(member.getBeverageSize())
			.createdDate(member.getDateAudit().getCreatedDate())
			.build();
	}

	@Getter
	@Setter
	@Builder
	private static class MyInfo {

		private String nickname;
		private String email;
		private String profileUrl;
		private String bio;
		private BeverageSize beverageSize;
		private LocalDateTime createdDate;
	}

	@Getter
	@Setter
	@Builder
	private static class ReviewInfo {

		private List<CafeTagType> tags;
		private CafeInfo cafeInfo;

		@Getter
		@Setter
		@Builder
		private static class CafeInfo {

			private Long id;
			private String imgUrl;
			private String name;
		}
	}
}
