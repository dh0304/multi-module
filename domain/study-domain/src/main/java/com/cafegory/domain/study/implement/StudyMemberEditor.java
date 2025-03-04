package com.cafegory.domain.study.implement;

import com.cafegory.domain.member.domain.MemberId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import com.cafegory.domain.study.domain.StudyId;
import com.cafegory.domain.study.domain.StudyMemberId;
import com.cafegory.domain.study.domain.StudyRole;
import com.cafegory.domain.study.repository.StudyMemberRepository;

@Component
@RequiredArgsConstructor
public class StudyMemberEditor {

	private final StudyMemberRepository studyMemberRepository;

	public StudyMemberId save(MemberId memberId, StudyId studyId, StudyRole studyRole) {
		return studyMemberRepository.save(memberId, studyId, studyRole);
	}
}
