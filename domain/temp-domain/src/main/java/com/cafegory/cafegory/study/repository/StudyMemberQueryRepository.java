package com.cafegory.cafegory.study.repository;

import com.cafegory.cafegory.member.domain.MemberId;
import com.cafegory.cafegory.study.domain.Participant;
import com.cafegory.cafegory.study.domain.StudyId;

import java.util.List;

public interface StudyMemberQueryRepository {

	List<Participant> findByMember_Id(MemberId memberId);

	List<Participant> findByStudy_Id(StudyId studyId);

	int countByCafeStudy_Id(StudyId studyId);
}
