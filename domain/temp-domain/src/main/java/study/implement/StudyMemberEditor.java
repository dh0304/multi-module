package study.implement;

import lombok.RequiredArgsConstructor;
import member.domain.MemberId;
import org.springframework.stereotype.Component;
import study.domain.StudyId;
import study.domain.StudyMemberId;
import study.domain.StudyRole;
import study.repository.StudyMemberRepository;

@Component
@RequiredArgsConstructor
public class StudyMemberEditor {

	private final StudyMemberRepository studyMemberRepository;

	public StudyMemberId save(MemberId memberId, StudyId studyId, StudyRole studyRole) {
		return studyMemberRepository.save(memberId, studyId, studyRole);
	}
}
