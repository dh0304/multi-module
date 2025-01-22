package study.repository;

import member.domain.MemberId;
import study.domain.Participant;
import study.domain.StudyId;

import java.util.List;

public interface StudyMemberQueryRepository {

	List<Participant> findByMember_Id(MemberId memberId);

	List<Participant> findByStudy_Id(StudyId studyId);

	int countByCafeStudy_Id(StudyId studyId);
}
