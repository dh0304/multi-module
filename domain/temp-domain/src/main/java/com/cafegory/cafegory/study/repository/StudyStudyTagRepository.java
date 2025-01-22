package com.cafegory.cafegory.study.repository;

import com.cafegory.cafegory.study.domain.StudyId;
import com.cafegory.cafegory.study.domain.StudyStudyTagId;
import com.cafegory.cafegory.study.domain.StudyTagId;

import java.time.LocalDateTime;
import java.util.List;

public interface StudyStudyTagRepository {

	List<StudyStudyTagId> saveAll(StudyId studyId, List<StudyTagId> studyTagIds);

	void remove(StudyId studyId, LocalDateTime now);

}
