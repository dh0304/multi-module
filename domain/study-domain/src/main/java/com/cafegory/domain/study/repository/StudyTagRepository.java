package com.cafegory.domain.study.repository;

import com.cafegory.domain.study.domain.CafeStudyTagType;
import com.cafegory.domain.study.domain.StudyTagId;

import java.util.List;

public interface StudyTagRepository {

	List<StudyTagId> countByTags(List<CafeStudyTagType> tags);
}
