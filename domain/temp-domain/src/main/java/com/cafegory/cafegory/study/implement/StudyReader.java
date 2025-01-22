package com.cafegory.cafegory.study.implement;

import com.cafegory.cafegory.common.SliceResponse;
import com.cafegory.cafegory.exception.CafegoryException;
import lombok.RequiredArgsConstructor;
import com.cafegory.cafegory.member.domain.MemberId;
import org.springframework.stereotype.Component;
import com.cafegory.cafegory.study.domain.Participant;
import com.cafegory.cafegory.study.domain.Study;
import com.cafegory.cafegory.study.domain.StudyId;
import com.cafegory.cafegory.study.repository.CafeStudySearchListRequest;
import com.cafegory.cafegory.study.repository.StudyQueryRepository;
import com.cafegory.cafegory.study.repository.StudySearchListResponse;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static com.cafegory.cafegory.exception.ExceptionType.*;


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
