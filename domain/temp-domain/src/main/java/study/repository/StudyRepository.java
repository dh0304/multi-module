package study.repository;

import cafe.domain.CafeId;
import member.domain.MemberId;
import study.domain.StudyContent;
import study.domain.StudyId;

import java.time.LocalDateTime;

public interface StudyRepository {

	StudyId save(StudyContent content, CafeId cafeId, MemberId memberId);

	void deleteWithCascade(StudyId studyId, MemberId memberId, LocalDateTime now);
}
