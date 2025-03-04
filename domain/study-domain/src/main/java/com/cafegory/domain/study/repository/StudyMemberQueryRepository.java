package com.cafegory.domain.study.repository;

import com.cafegory.domain.member.domain.MemberId;
import com.cafegory.domain.study.domain.Participant;
import com.cafegory.domain.study.domain.StudyId;

import java.util.List;

public interface StudyMemberQueryRepository {

	List<Participant> findByMember_Id(MemberId memberId);

	List<Participant> findByStudy_Id(StudyId studyId);

	int countByCafeStudy_Id(StudyId studyId);
}
