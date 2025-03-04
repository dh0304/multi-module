package com.cafegory.domain.study.implement;

import com.cafegory.domain.cafe.domain.CafeId;
import com.cafegory.domain.member.domain.MemberId;
import com.cafegory.domain.study.domain.*;
import com.cafegory.domain.study.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static com.cafegory.domain.common.exception.ExceptionType.STUDY_ONCE_NAME_EMPTY_OR_WHITESPACE;

@Component
@RequiredArgsConstructor
public class StudyEditor {

	private final StudyRepository studyRepository;
	private final StudyQueryRepository studyQueryRepository;

	private final StudyTagRepository studyTagRepository;
	private final StudyStudyTagRepository studyStudyTagRepository;
	private final StudyMemberRepository studyMemberRepository;

	private final StudyValidator studyValidator;

	@Transactional
	public StudyId saveWithCascade(StudyContent content, CafeId cafeId, MemberId memberId) {
		validateStudyDetails(content);

		StudyId savedStudyId = studyRepository.save(content, cafeId, memberId);
		List<StudyTagId> studyTagIds = studyTagRepository.countByTags(content.getTags());
		studyStudyTagRepository.saveAll(savedStudyId, studyTagIds);

		return savedStudyId;
	}

	private void validateStudyDetails(StudyContent content) {
		studyValidator.validateEmptyOrWhiteSpace(content.getName(), STUDY_ONCE_NAME_EMPTY_OR_WHITESPACE);
		studyValidator.validateNameLength(content.getName());
		studyValidator.validateMaxParticipants(content.getMaxParticipantCount());
	}

	@Transactional
	public void removeWithCascade(StudyId studyId, MemberId candidateMemberId, LocalDateTime now) {
		Study study = studyQueryRepository.findById(studyId);

		List<Coordinator> coordinators = studyQueryRepository.findCoordinatorsBy(candidateMemberId);

		studyValidator.validateCoordinatorIsInStudy(study, coordinators);

		studyMemberRepository.remove(studyId, candidateMemberId, now);
		studyStudyTagRepository.remove(studyId, now);

		studyRepository.deleteWithCascade(studyId, candidateMemberId, now);
	}
}
