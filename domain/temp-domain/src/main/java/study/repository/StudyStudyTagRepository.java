package study.repository;

import study.domain.StudyId;
import study.domain.StudyStudyTagId;
import study.domain.StudyTagId;

import java.time.LocalDateTime;
import java.util.List;

public interface StudyStudyTagRepository {

	List<StudyStudyTagId> saveAll(StudyId studyId, List<StudyTagId> studyTagIds);

	void remove(StudyId studyId, LocalDateTime now);

}
