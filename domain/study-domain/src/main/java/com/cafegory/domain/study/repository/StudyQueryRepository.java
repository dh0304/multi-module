package com.cafegory.domain.study.repository;

import com.cafegory.domain.common.SliceResponse;
import com.cafegory.domain.member.domain.MemberId;
import com.cafegory.domain.study.domain.Coordinator;
import com.cafegory.domain.study.domain.Study;
import com.cafegory.domain.study.domain.StudyId;
import com.cafegory.domain.study.dto.CafeStudySearchListRequest;
import com.cafegory.domain.study.dto.StudySearchListResponse;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface StudyQueryRepository {

	Study findById(StudyId studyId);

	List<Coordinator> findCoordinatorsBy(MemberId memberId);

	Optional<Study> findWithMember(StudyId studyId);

	int findViewCountBy(StudyId studyId);

	List<Study> findUpcomingsWithMemberBy(List<StudyId> studyIds, LocalDateTime now);

//	SliceResponse<CafeStudySearchListResponse> findCafeStudies(CafeStudySearchListRequest request);

	SliceResponse<StudySearchListResponse> findStudies(CafeStudySearchListRequest request);

}
