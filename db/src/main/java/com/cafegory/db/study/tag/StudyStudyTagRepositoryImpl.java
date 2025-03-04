package com.cafegory.db.study.tag;

import com.cafegory.domain.study.domain.StudyId;
import com.cafegory.domain.study.domain.StudyStudyTagId;
import com.cafegory.domain.study.domain.StudyTagId;
import com.cafegory.domain.study.repository.StudyStudyTagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class StudyStudyTagRepositoryImpl implements StudyStudyTagRepository {

	private final StudyStudyTagJpaRepository studyStudyTagJpaRepository;

	@Override
	public List<StudyStudyTagId> saveAll(StudyId studyId, List<StudyTagId> studyTagIds) {
		List<CafeStudyCafeStudyTagEntity> studyStudyTags = studyStudyTagJpaRepository.saveAll(
			buildCafeStudyTags(studyId, studyTagIds)
		);

		return studyStudyTags.stream()
			.map(studyStudyTag -> new StudyStudyTagId((studyStudyTag.getId())))
			.collect(Collectors.toList());
	}

	@Override
	public void remove(StudyId studyId, LocalDateTime now) {
		studyStudyTagJpaRepository.findByCafeStudy_Id(studyId.getId())
			.forEach(studyTag -> studyTag.softDelete(now));
	}

	private List<CafeStudyCafeStudyTagEntity> buildCafeStudyTags(StudyId studyId, List<StudyTagId> studyTagIds) {
		return studyTagIds.stream()
			.map(studyTagId -> new CafeStudyCafeStudyTagEntity(studyId.getId(), studyTagId.getId()))
			.collect(Collectors.toList());
	}
}
