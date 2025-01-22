package com.cafegory.cafegory.study.repository;

import com.cafegory.cafegory.study.domain.CafeStudyTagType;
import com.cafegory.cafegory.study.domain.StudyTagId;

import java.util.List;

public interface StudyTagRepository {

	List<StudyTagId> countByTags(List<CafeStudyTagType> tags);
}
