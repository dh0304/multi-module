package study.repository;

import common.SliceResponse;
import study.domain.Study;
import study.domain.StudyId;

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
