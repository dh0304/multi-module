package com.cafegory.db.study.studymember;

import com.cafegory.domain.member.domain.MemberId;
import com.cafegory.domain.study.domain.StudyId;
import com.cafegory.domain.study.domain.StudyMemberId;
import com.cafegory.domain.study.domain.StudyRole;
import com.cafegory.domain.study.repository.StudyMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
@RequiredArgsConstructor
public class StudyMemberRepositoryImpl implements StudyMemberRepository {

	private final StudyMemberJpaRepository studyMemberJpaRepository;

	@Override
	public StudyMemberId save(MemberId memberId, StudyId studyId, StudyRole studyRole) {
		Long participantId = studyMemberJpaRepository.save(
			new CafeStudyMemberEntity(studyId.getId(), memberId.getId())
		).getId();

		return new StudyMemberId(participantId);
	}

	@Override
	public void remove(StudyId studyId, MemberId memberId, LocalDateTime now) {
		studyMemberJpaRepository.findByCafeStudy_IdAndMember_Id(studyId.getId(), memberId.getId())
			.orElseThrow(() -> new IllegalArgumentException("회원이 참여중인 스터디가 존재하지 않습니다."))
			.softDelete(now);
	}
}
