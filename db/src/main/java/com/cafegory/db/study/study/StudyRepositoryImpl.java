package com.cafegory.db.study.study;

import com.cafegory.domain.cafe.domain.CafeId;
import com.cafegory.domain.member.domain.MemberId;
import com.cafegory.domain.study.domain.StudyContent;
import com.cafegory.domain.study.domain.StudyId;
import com.cafegory.domain.study.repository.StudyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
@RequiredArgsConstructor
public class StudyRepositoryImpl implements StudyRepository {

	private final StudyJpaRepository studyJpaRepository;

	@Override
	public StudyId save(StudyContent content, CafeId cafeId, MemberId memberId) {
		CafeStudyEntity studyEntity = studyJpaRepository.save(
			new CafeStudyEntity(content, cafeId.getId(), memberId.getId()));
		return new StudyId(studyEntity.getId());
	}

	@Override
	public void deleteWithCascade(StudyId studyId, MemberId memberId, LocalDateTime now) {
		studyJpaRepository.findById(studyId.getId())
			.orElseThrow(() -> new IllegalArgumentException("해당 카공을 찾을 수 없습니다."))
			.softDelete(now);
	}
}
