package com.cafegory.domain.study.repository;

import com.cafegory.domain.member.domain.MemberId;
import com.cafegory.domain.study.domain.StudyId;
import com.cafegory.domain.study.domain.StudyMemberId;
import com.cafegory.domain.study.domain.StudyRole;

import java.time.LocalDateTime;

public interface StudyMemberRepository {

	StudyMemberId save(MemberId memberId, StudyId studyId, StudyRole studyRole);

	void remove(StudyId studyId, MemberId memberId, LocalDateTime now);
}
