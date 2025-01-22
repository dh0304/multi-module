package com.cafegory.cafegory.study.implement;

import lombok.RequiredArgsConstructor;
import com.cafegory.cafegory.member.domain.MemberId;
import org.springframework.stereotype.Component;
import com.cafegory.cafegory.study.domain.StudyId;
import com.cafegory.cafegory.study.domain.StudyMemberId;
import com.cafegory.cafegory.study.domain.StudyRole;
import com.cafegory.cafegory.study.repository.StudyMemberRepository;

@Component
@RequiredArgsConstructor
public class StudyMemberEditor {

	private final StudyMemberRepository studyMemberRepository;

	public StudyMemberId save(MemberId memberId, StudyId studyId, StudyRole studyRole) {
		return studyMemberRepository.save(memberId, studyId, studyRole);
	}
}
