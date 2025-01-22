package com.cafegory.cafegory.study.repository;

import com.cafegory.cafegory.member.domain.MemberId;
import com.cafegory.cafegory.study.domain.StudyId;
import com.cafegory.cafegory.study.domain.StudyMemberId;
import com.cafegory.cafegory.study.domain.StudyRole;

import java.time.LocalDateTime;

public interface StudyMemberRepository {

	StudyMemberId save(MemberId memberId, StudyId studyId, StudyRole studyRole);

	void remove(StudyId studyId, MemberId memberId, LocalDateTime now);
}
