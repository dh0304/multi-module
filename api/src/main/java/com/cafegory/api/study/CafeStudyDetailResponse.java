package com.cafegory.api.study;

import com.cafegory.domain.cafe.domain.Cafe;
import com.cafegory.domain.study.domain.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CafeStudyDetailResponse {

	private CafeStudyInfo cafeStudyInfo;
	private CoordinatorInfo coordinatorInfo;
	private CafeInfo cafeInfo;

	public static CafeStudyDetailResponse of(
			Cafe cafe, Study study, int viewCount, int participantCount
	) {
		CafeStudyDetailResponse response = new CafeStudyDetailResponse();

		response.cafeStudyInfo = createCafeStudyInfo(study, viewCount, participantCount);
		response.coordinatorInfo = createCoordinatorInfo(study);
		response.cafeInfo = createCafeInfo(cafe);

		return response;
	}

	private static CafeInfo createCafeInfo(Cafe cafe) {
		return CafeInfo.builder()
			.id(cafe.getId().getId())
			.imgUrl(cafe.getImgUrl())
			.name(cafe.getName())
			.build();
	}

	private static CoordinatorInfo createCoordinatorInfo(Study study) {
		Coordinator coordinator = study.getCoordinator();

		return CoordinatorInfo.builder()
			.id(coordinator.getId().getId())
			.nickname(coordinator.getNickname())
			.build();
	}

	private static CafeStudyInfo createCafeStudyInfo(
		Study study, int viewCount, int participantCount
	) {
		StudyContent content = study.getContent();

		return CafeStudyInfo.builder()
			.id(study.getId().getId())
			.name(content.getName())
			.createdDate(study.getDateAudit().getCreatedDate())
			.modifiedDate(study.getDateAudit().getModifiedDate())
			.startDateTime(content.getSchedule().getStartDateTime())
			.endDateTime(content.getSchedule().getEndDateTime())
			.maximumParticipants(content.getMaxParticipantCount())
			.currentParticipants(participantCount)
			.memberComms(content.getMemberComms())
			.views(viewCount)
			.introduction(content.getIntroduction())
			.tag(content.getTags())
			.build();
	}

	@Getter
	@Setter
	@Builder
	private static class CafeStudyInfo {

		private Long id;
		private String name;
		private LocalDateTime createdDate;
		private LocalDateTime modifiedDate;
		private LocalDateTime startDateTime;
		private LocalDateTime endDateTime;
		private int maximumParticipants;
		private int currentParticipants;
		private MemberComms memberComms;
		private int views;
		private String introduction;
		private List<CafeStudyTagType> tag;
	}

	@Getter
	@Setter
	@Builder
	private static class CoordinatorInfo {

		private Long id;
		private String nickname;
	}

	@Getter
	@Setter
	@Builder
	private static class CafeInfo {

		private Long id;
		private String imgUrl;
		private String name;
	}

	@Getter
	@Setter
	@Builder
	private static class Comment {

		private WriterInfo writerInfo;
		private CommentInfo commentInfo;
		private List<Comment> replies;

		@Getter
		@Setter
		@Builder
		private static class WriterInfo {

			private Long id;
			private String nickname;
			private String profileUrl;
		}

		@Getter
		@Setter
		@Builder
		private static class CommentInfo {

			private Long id;
			private String content;
			private LocalDateTime createdDate;
		}
	}
}
