package com.cafegory.db.study.studymember;

import com.cafegory.domain.member.domain.MemberId;
import com.cafegory.domain.study.domain.Participant;
import com.cafegory.domain.study.domain.StudyId;
import com.cafegory.domain.study.repository.StudyMemberQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class StudyMemberQueryRepositoryImpl implements StudyMemberQueryRepository {

	private final StudyMemberJpaRepository studyMemberJpaRepository;

	@Override
	public List<Participant> findByMember_Id(MemberId memberId) {
		return studyMemberJpaRepository.findByMember_Id(memberId.getId())
			.stream().map(CafeStudyMemberEntity::toParticipant)
			.collect(Collectors.toList());
	}

	@Override
	public List<Participant> findByStudy_Id(StudyId studyId) {
		return studyMemberJpaRepository.findByCafeStudy_Id(studyId.getId())
			.stream().map(CafeStudyMemberEntity::toParticipant)
			.collect(Collectors.toList());
	}

	@Override
	public int countByCafeStudy_Id(StudyId studyId) {
		return studyMemberJpaRepository.countByCafeStudy_Id(studyId.getId());
	}
}
