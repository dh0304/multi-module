package study.repository;

import study.domain.CafeStudyTagType;
import study.domain.StudyTagId;

import java.util.List;

public interface StudyTagRepository {

	List<StudyTagId> countByTags(List<CafeStudyTagType> tags);
}
