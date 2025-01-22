package study.repository;

import member.domain.MemberId;
import study.domain.StudyId;
import study.domain.StudyMemberId;
import study.domain.StudyRole;

import java.time.LocalDateTime;

public interface StudyMemberRepository {

	StudyMemberId save(MemberId memberId, StudyId studyId, StudyRole studyRole);

	void remove(StudyId studyId, MemberId memberId, LocalDateTime now);
}
