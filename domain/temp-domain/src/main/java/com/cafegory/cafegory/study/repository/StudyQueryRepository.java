package com.cafegory.cafegory.study.repository;

import com.cafegory.cafegory.common.SliceResponse;
import com.cafegory.cafegory.study.domain.Study;
import com.cafegory.cafegory.study.domain.StudyId;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface StudyQueryRepository {

	Study findById(StudyId studyId);

	Optional<Study> findWithMember(StudyId studyId);

	int findViewCountBy(StudyId studyId);

	List<Study> findUpcomingsWithMemberBy(List<StudyId> studyIds, LocalDateTime now);

//	SliceResponse<CafeStudySearchListResponse> findCafeStudies(CafeStudySearchListRequest request);

	SliceResponse<StudySearchListResponse> findStudies(CafeStudySearchListRequest request);

}
