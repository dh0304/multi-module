package com.cafegory.db.study.tag;

import com.cafegory.domain.study.domain.CafeStudyTagType;
import com.cafegory.domain.study.domain.StudyTagId;
import com.cafegory.domain.study.repository.StudyTagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class StudyTagRepositoryImpl implements StudyTagRepository {

	private final StudyTagJpaRepository cafeStudyTagJpaRepository;

	@Override
	public List<StudyTagId> countByTags(List<CafeStudyTagType> tags) {
		return cafeStudyTagJpaRepository.countByTags(tags).stream()
			.map(StudyTagId::new)
			.collect(Collectors.toList());
	}
}
