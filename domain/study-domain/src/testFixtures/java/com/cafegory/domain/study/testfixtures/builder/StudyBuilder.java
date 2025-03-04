package com.cafegory.domain.study.testfixtures.builder;

import com.cafegory.domain.cafe.domain.CafeId;
import com.cafegory.domain.common.DateAudit;
import com.cafegory.domain.common.testfixtures.builder.DateAuditBuilder;
import com.cafegory.domain.study.domain.*;

import static com.cafegory.domain.study.testfixtures.builder.CoordinatorBuilder.aCoordinator;
import static com.cafegory.domain.study.testfixtures.builder.StudyContentBuilder.aStudyContent;

public class StudyBuilder {

	private StudyId id = new StudyId(1L);
	private StudyContent content = aStudyContent().build();
	private CafeId cafeId = new CafeId(1L);
	private Coordinator coordinator = aCoordinator().build();
	private RecruitmentStatus recruitmentStatus = RecruitmentStatus.OPEN;

	private DateAudit dateAudit = DateAuditBuilder.aDateAudit().build();

	private StudyBuilder() {
	}

	private StudyBuilder(StudyBuilder copy) {
		this.id = copy.id;
		this.content = copy.content;
		this.cafeId = copy.cafeId;
		this.coordinator = copy.coordinator;
		this.recruitmentStatus = copy.recruitmentStatus;
		this.dateAudit = copy.dateAudit;
	}

	public StudyBuilder but() {
		return new StudyBuilder(this);
	}

	public static StudyBuilder aStudy() {
		return new StudyBuilder();
	}

	public StudyBuilder withId(Long id) {
		this.id = new StudyId(id);
		return this;
	}

	public StudyBuilder with(StudyContentBuilder studyContentBuilder) {
		this.content = studyContentBuilder.build();
		return this;
	}

	public StudyBuilder withCafeId(Long cafeId) {
		this.cafeId = new CafeId(cafeId);
		return this;
	}

	public StudyBuilder with(CoordinatorBuilder coordinatorBuilder) {
		this.coordinator = coordinatorBuilder.build();
		return this;
	}

	public StudyBuilder withRecruitmentStatus(RecruitmentStatus recruitmentStatus) {
		this.recruitmentStatus = recruitmentStatus;
		return this;
	}

	public StudyBuilder withDateAudit(DateAudit dateAudit) {
		this.dateAudit = dateAudit;
		return this;
	}

	public Study build() {
		return Study.builder()
			.id(this.id)
			.content(this.content)
			.cafeId(this.cafeId)
			.coordinator(this.coordinator)
			.recruitmentStatus(this.recruitmentStatus)
			.dateAudit(this.dateAudit)
			.build();
	}
}
