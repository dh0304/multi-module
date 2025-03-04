package com.cafegory.db.study.studymember;

import com.cafegory.db.BaseEntity;
import com.cafegory.db.member.MemberEntity;
import com.cafegory.db.study.study.CafeStudyEntity;
import com.cafegory.domain.study.domain.*;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Where;

@Builder
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Setter
@Where(clause = "deleted_date IS NULL")
@Table(name = "cafe_study_member", uniqueConstraints = {
	@UniqueConstraint(name = "unique_cafe_study_member", columnNames = {"cafe_study_id", "member_id"})})
public class CafeStudyMemberEntity extends BaseEntity {

	@Id
	@GeneratedValue
	@Column(name = "cafe_study_member_id")
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cafe_study_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
	private CafeStudyEntity cafeStudy;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
	private MemberEntity member;

	@Enumerated(EnumType.STRING)
	private StudyRole studyRole;

	@Builder.Default
	@Enumerated(EnumType.STRING)
	private Attendance attendance = Attendance.YES;

	public CafeStudyMemberEntity(Long studyId, Long memberId) {
		this.cafeStudy = new CafeStudyEntity(studyId);
		this.member = new MemberEntity(memberId);
	}

	public Participant toParticipant() {
		return Participant.builder()
			.id(new StudyMemberId(this.id))
			.studyId(new StudyId(this.cafeStudy.getId()))
			.studyRole(this.studyRole)
			.content(ParticipantContent.builder()
				.attendance(this.attendance)
				.build())
			.build();
	}
}
