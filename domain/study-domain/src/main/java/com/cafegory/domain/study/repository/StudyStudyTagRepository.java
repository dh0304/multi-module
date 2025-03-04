package com.cafegory.domain.study.repository;

import com.cafegory.domain.study.domain.StudyId;
import com.cafegory.domain.study.domain.StudyStudyTagId;
import com.cafegory.domain.study.domain.StudyTagId;

import java.time.LocalDateTime;
import java.util.List;

public interface StudyStudyTagRepository {

	List<StudyStudyTagId> saveAll(StudyId studyId, List<StudyTagId> studyTagIds);

	void remove(StudyId studyId, LocalDateTime now);

}
