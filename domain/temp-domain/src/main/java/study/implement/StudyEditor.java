package study.implement;

import cafe.domain.CafeId;
import lombok.RequiredArgsConstructor;
import member.domain.MemberId;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import study.domain.*;
import study.repository.*;

import java.time.LocalDateTime;
import java.util.List;

import static exception.ExceptionType.STUDY_ONCE_NAME_EMPTY_OR_WHITESPACE;

@Component
@RequiredArgsConstructor
public class StudyEditor {

	private final StudyRepository studyRepository;
	private final StudyQueryRepository studyQueryRepository;

	private final StudyTagRepository studyTagRepository;
	private final StudyStudyTagRepository studyStudyTagRepository;
	private final StudyMemberRepository studyMemberRepository;
	private final CoordinatorRepository coordinatorRepository;

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
	public void removeWithCascade(StudyId studyId, MemberId candidateCoordinatorId, LocalDateTime now) {
		Study study = studyQueryRepository.findById(studyId);

		List<Coordinator> coordinators = coordinatorRepository.findBy(candidateCoordinatorId);

		studyValidator.validateCoordinatorIsInStudy(study, coordinators);

		studyMemberRepository.remove(studyId, candidateCoordinatorId, now);
		studyStudyTagRepository.remove(studyId, now);

		studyRepository.deleteWithCascade(studyId, candidateCoordinatorId, now);
	}
}
