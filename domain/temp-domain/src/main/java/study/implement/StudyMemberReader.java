package study.implement;

import lombok.RequiredArgsConstructor;
import member.domain.MemberId;
import org.springframework.stereotype.Component;
import study.domain.Participant;
import study.domain.StudyId;
import study.domain.StudyMemberId;
import study.repository.StudyMemberQueryRepository;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class StudyMemberReader {

	private final StudyMemberQueryRepository studyMemberQueryRepository;

	public int loadParticipantCount(StudyId studyId) {
		return studyMemberQueryRepository.countByCafeStudy_Id(studyId);
	}

	public List<Participant> readMyUpcomingsBy(MemberId memberId) {
		return studyMemberQueryRepository.findByMember_Id(memberId);
	}

	// study에 참여한 참여자를 가져온다.
	public List<StudyMemberId> readParticipantIdsBy(StudyId studyId) {
		return studyMemberQueryRepository.findByStudy_Id(studyId).stream()
			.map(participant -> new StudyMemberId(participant.getId().getId()))
			.collect(Collectors.toList());
	}

	public int readParticipantCountBy(StudyId studyId) {
		return readParticipantIdsBy(studyId).size();
	}
}
