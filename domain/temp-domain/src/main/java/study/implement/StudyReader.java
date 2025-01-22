package study.implement;

import common.SliceResponse;
import exception.CafegoryException;
import exception.ExceptionType;
import lombok.RequiredArgsConstructor;
import member.domain.MemberId;
import org.springframework.stereotype.Component;
import study.domain.Participant;
import study.domain.Study;
import study.domain.StudyId;
import study.repository.CafeStudySearchListRequest;
import study.repository.StudyQueryRepository;
import study.repository.StudySearchListResponse;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static exception.ExceptionType.*;


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
