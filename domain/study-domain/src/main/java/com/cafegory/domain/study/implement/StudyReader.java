package com.cafegory.domain.study.implement;

import com.cafegory.domain.common.SliceResponse;
import com.cafegory.domain.common.exception.CafegoryException;
import com.cafegory.domain.member.domain.MemberId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import com.cafegory.domain.study.domain.Participant;
import com.cafegory.domain.study.domain.Study;
import com.cafegory.domain.study.domain.StudyId;
import com.cafegory.domain.study.dto.CafeStudySearchListRequest;
import com.cafegory.domain.study.repository.StudyQueryRepository;
import com.cafegory.domain.study.dto.StudySearchListResponse;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static com.cafegory.domain.common.exception.ExceptionType.CAFE_STUDY_NOT_FOUND;


@Component
@RequiredArgsConstructor
public class StudyReader {

	private final StudyMemberReader studyMemberReader;

	private final StudyQueryRepository studyQueryRepository;

	public Study read(StudyId studyId) {
		return studyQueryRepository.findWithMember(studyId)
			.orElseThrow(() -> new CafegoryException(CAFE_STUDY_NOT_FOUND));
	}

	public List<Study> readUpcomingBy(MemberId memberId, LocalDateTime now) {
		List<Participant> upcomings = studyMemberReader.readMyUpcomingsBy(memberId);
		List<StudyId> studyIds = upcomings.stream()
			.map(participant -> new StudyId(participant.getStudyId().getId())).collect(Collectors.toList());

		return studyQueryRepository.findUpcomingsWithMemberBy(studyIds, now);
	}

	public SliceResponse<StudySearchListResponse> searchCafeStudies(CafeStudySearchListRequest request) {
		return studyQueryRepository.findStudies(request);
	}

	public int readViewCountBy(StudyId studyId) {
		return studyQueryRepository.findViewCountBy(studyId);
	}
}
