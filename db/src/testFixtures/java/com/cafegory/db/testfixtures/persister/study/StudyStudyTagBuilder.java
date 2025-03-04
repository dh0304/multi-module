package com.cafegory.db.testfixtures.persister.study;

import com.cafegory.db.study.study.CafeStudyEntity;
import com.cafegory.db.study.tag.CafeStudyCafeStudyTagEntity;
import com.cafegory.db.study.tag.CafeStudyTagEntity;
import com.cafegory.db.study.tag.StudyStudyTagJpaRepository;

public class StudyStudyTagBuilder {

	private CafeStudyEntity study;
	private CafeStudyTagEntity studyTag;

	private StudyStudyTagBuilder() {
	}

	private StudyStudyTagBuilder(StudyStudyTagBuilder copy) {
		this.study = copy.study;
		this.studyTag = copy.studyTag;
	}

	public StudyStudyTagBuilder but() {
		return new StudyStudyTagBuilder(this);
	}

	public static StudyStudyTagBuilder aStudyStudyTag() {
		return new StudyStudyTagBuilder();
	}

	public StudyStudyTagBuilder withStudy(CafeStudyEntity study) {
		this.study = study;
		return this;
	}

	public StudyStudyTagBuilder withTag(CafeStudyTagEntity studyTag) {
		this.studyTag = studyTag;
		return this;
	}

	public CafeStudyCafeStudyTagEntity build() {
		return CafeStudyCafeStudyTagEntity.builder()
			.cafeStudy(this.study)
			.cafeStudyTag(this.studyTag)
			.build();
	}

	public static class StudyStudyTagRepoHolder {
		private static StudyStudyTagJpaRepository studyStudyTagRepository;

		public static void init(StudyStudyTagJpaRepository studyStudyTagRepo) {
			studyStudyTagRepository = studyStudyTagRepo;
		}
	}

	public CafeStudyCafeStudyTagEntity persist() {
		return StudyStudyTagRepoHolder.studyStudyTagRepository.save(build());
	}
}
