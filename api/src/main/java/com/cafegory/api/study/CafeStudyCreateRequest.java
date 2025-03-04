package com.cafegory.api.study;

import com.cafegory.domain.cafe.domain.CafeId;
import com.cafegory.domain.study.domain.CafeStudyTagType;
import com.cafegory.domain.study.domain.MemberComms;
import com.cafegory.domain.study.domain.Schedule;
import com.cafegory.domain.study.domain.StudyContent;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CafeStudyCreateRequest {
	@NotBlank
	private String name;
	private Long cafeId;
	private LocalDateTime startDateTime;
	private LocalDateTime endDateTime;
	private MemberComms memberComms;
	private int maxParticipants;
	@NotBlank
	private String introduction;
	private List<CafeStudyTagType> tags;

	public StudyContent toStudyContent() {
		return StudyContent.builder()
			.name(this.name)
			.schedule(
				Schedule.builder()
					.startDateTime(this.startDateTime)
					.endDateTime(this.endDateTime)
					.build()
			)
			.memberComms(this.memberComms)
			.maxParticipantCount(this.maxParticipants)
			.introduction(this.introduction)
			.tags(this.tags)
			.build();
	}

	public CafeId toCafeId() {
		return new CafeId(this.cafeId);
	}
}

