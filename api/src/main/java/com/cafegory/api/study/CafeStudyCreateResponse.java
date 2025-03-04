package com.cafegory.api.study;

import com.cafegory.domain.study.domain.MemberComms;
import com.cafegory.domain.study.domain.RecruitmentStatus;
import com.cafegory.domain.study.domain.Study;
import com.cafegory.domain.study.domain.StudyContent;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CafeStudyCreateResponse {
	private long cafeStudyId;
	private String name;
	private long cafeId;
	private long memberId;
	private LocalDateTime startDateTime;
	private LocalDateTime endDateTime;
	private MemberComms memberComms;
	private int maxParticipants;
	private int nowParticipants;
	private String introduction;
	private int views;
	private RecruitmentStatus recruitmentStatus;

	public static CafeStudyCreateResponse from(Study study) {
		StudyContent content = study.getContent();

		return CafeStudyCreateResponse.builder()
			.name(content.getName())
			.cafeId(study.getCafeId().getId())
			.memberId(study.getCoordinator().getId().getId())
			.startDateTime(content.getStartDateTime())
			.endDateTime(content.getEndDateTime())
			.memberComms(content.getMemberComms())
			.maxParticipants(content.getMaxParticipantCount())
			.nowParticipants(1)
			.introduction(content.getIntroduction())
			.views(0)
			.recruitmentStatus(study.getRecruitmentStatus())
			.build();
	}

	@Builder
	private CafeStudyCreateResponse(long cafeStudyId, String name, long cafeId, long memberId,
		LocalDateTime startDateTime, LocalDateTime endDateTime, MemberComms memberComms, int maxParticipants,
		int nowParticipants, String introduction, int views, RecruitmentStatus recruitmentStatus) {
		this.cafeStudyId = cafeStudyId;
		this.name = name;
		this.cafeId = cafeId;
		this.memberId = memberId;
		this.startDateTime = startDateTime;
		this.endDateTime = endDateTime;
		this.memberComms = memberComms;
		this.maxParticipants = maxParticipants;
		this.nowParticipants = nowParticipants;
		this.introduction = introduction;
		this.views = views;
		this.recruitmentStatus = recruitmentStatus;
	}
}
