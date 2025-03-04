package com.cafegory.db.study.study;

import com.cafegory.db.BaseEntity;
import com.cafegory.db.cafe.cafe.CafeEntity;
import com.cafegory.domain.cafe.domain.CafeId;
import com.cafegory.domain.common.DateAudit;
import com.cafegory.db.member.MemberEntity;
import com.cafegory.db.study.studymember.CafeStudyMemberEntity;
import com.cafegory.db.study.tag.CafeStudyCafeStudyTagEntity;
import com.cafegory.domain.member.domain.MemberId;
import com.cafegory.domain.study.domain.*;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Where;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Builder
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Setter
@Where(clause = "deleted_date IS NULL")
@Table(name = "cafe_study")
public class CafeStudyEntity extends BaseEntity {

	@Id
	@GeneratedValue
	@Column(name = "cafe_study_id")
	private Long id;

	private String name;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cafe_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
	private CafeEntity cafe;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
	private MemberEntity member;

	@Embedded
	private StudyPeriod studyPeriod;

	@Enumerated(EnumType.STRING)
	private MemberComms memberComms;

	private int maxParticipants;
	private String introduction;
	private int views;

	@Builder.Default
	@Enumerated(EnumType.STRING)
	private RecruitmentStatus recruitmentStatus = RecruitmentStatus.OPEN;

	@Builder.Default
	@OneToMany(mappedBy = "cafeStudy")
	private List<CafeStudyMemberEntity> cafeStudyMembers = new ArrayList<>();

	@Builder.Default
	@OneToMany(mappedBy = "cafeStudy")
	private List<CafeStudyCafeStudyTagEntity> cafeStudyCafeStudyTags = new ArrayList<>();

	public CafeStudyEntity(Long studyId) {
		this.id = studyId;
	}

	public CafeStudyEntity(StudyContent content, Long cafeId, Long memberId) {
		this.name = content.getName();
		this.cafe = new CafeEntity(cafeId);
		this.member = new MemberEntity(memberId);
		this.studyPeriod = StudyPeriod.builder()
			.startDateTime(content.getStartDateTime())
			.endDateTime(content.getEndDateTime())
			.build();
		this.memberComms = content.getMemberComms();
		this.maxParticipants = content.getMaxParticipantCount();
		this.introduction = content.getIntroduction();
		this.views = 0;
		this.recruitmentStatus = RecruitmentStatus.OPEN;
	}

	public Study toStudy() {
		return Study.builder()
			.id(new StudyId(this.id))
			.content(
				StudyContent.builder()
					.name(this.name)
					.schedule(
						Schedule.builder()
							.startDateTime(this.getStudyPeriod().getStartDateTime())
							.endDateTime(this.getStudyPeriod().getEndDateTime())
							.build()
					)
					.memberComms(this.memberComms)
					.maxParticipantCount(this.maxParticipants)
					.introduction(this.introduction)
					.tags(this.cafeStudyCafeStudyTags.stream()
						.map(cafeTag -> cafeTag.getCafeStudyTag().getType())
						.collect(Collectors.toList()))
					.build()

			)
			.cafeId(new CafeId(this.cafe.getId()))
			.coordinator(
				Coordinator.builder()
					.id(new MemberId(this.member.getId()))
					.nickname(this.member.getNickname())
					.build())
			.recruitmentStatus(this.recruitmentStatus)
			.dateAudit(
				DateAudit.builder()
					.createdDate(this.getCreatedDate())
					.modifiedDate(this.getLastModifiedDate())
					.build()
			)
			.build();
	}

	public Coordinator toCoordinator() {
		return Coordinator.builder()
			.id(new MemberId(member.getId()))
			.nickname(member.getNickname())
			.build();
	}
}
