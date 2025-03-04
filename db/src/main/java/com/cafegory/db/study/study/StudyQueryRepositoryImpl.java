package com.cafegory.db.study.study;

import com.cafegory.domain.common.SliceResponse;
import com.cafegory.domain.common.exception.CafegoryException;
import com.cafegory.domain.member.domain.MemberId;
import com.cafegory.domain.study.domain.Coordinator;
import com.cafegory.domain.study.domain.Study;
import com.cafegory.domain.study.domain.StudyId;
import com.cafegory.domain.study.dto.CafeStudySearchListRequest;
import com.cafegory.domain.study.repository.StudyQueryRepository;
import com.cafegory.domain.study.dto.StudySearchListResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.cafegory.domain.common.exception.ExceptionType.CAFE_STUDY_NOT_FOUND;

@Repository
@RequiredArgsConstructor
public class StudyQueryRepositoryImpl implements StudyQueryRepository {

	private final StudyJpaRepository studyJpaRepository;
	private final StudyQueryDslRepository studyQueryDslRepository;

	@Override
	public Study findById(StudyId studyId) {
		return studyJpaRepository.findById(studyId.getId())
			.orElseThrow(() -> new CafegoryException(CAFE_STUDY_NOT_FOUND))
			.toStudy();
	}

	@Override
	public List<Coordinator> findCoordinatorsBy(MemberId memberId) {
		return studyJpaRepository.findByMember_Id(memberId.getId()).stream()
			.map(CafeStudyEntity::toCoordinator)
			.collect(Collectors.toList());
	}

	@Override
	public Optional<Study> findWithMember(StudyId studyId) {
		return studyJpaRepository.findWithMember(studyId.getId()).map(CafeStudyEntity::toStudy);
	}

	@Override
	public int findViewCountBy(StudyId studyId) {
		return studyJpaRepository.findViewsById(studyId.getId());
	}

	@Override
	public List<Study> findUpcomingsWithMemberBy(List<StudyId> studyIds, LocalDateTime now) {
		List<Long> ids = studyIds.stream()
			.map(StudyId::getId)
			.collect(Collectors.toList());

		return studyJpaRepository.findUpcomingsWithMemberBy(ids, now).stream()
			.map(CafeStudyEntity::toStudy)
			.collect(Collectors.toList());
	}

	@Override
	public SliceResponse<StudySearchListResponse> findStudies(CafeStudySearchListRequest request) {
		return studyQueryDslRepository.findStudies(request);
	}
}
