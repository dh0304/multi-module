package com.cafegory.domain.study.repository;

import com.cafegory.domain.cafe.domain.CafeId;
import com.cafegory.domain.member.domain.MemberId;
import com.cafegory.domain.study.domain.StudyContent;
import com.cafegory.domain.study.domain.StudyId;

import java.time.LocalDateTime;

public interface StudyRepository {

	StudyId save(StudyContent content, CafeId cafeId, MemberId memberId);

	void deleteWithCascade(StudyId studyId, MemberId memberId, LocalDateTime now);
}
