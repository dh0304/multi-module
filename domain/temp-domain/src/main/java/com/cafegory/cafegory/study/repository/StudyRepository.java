package com.cafegory.cafegory.study.repository;

import com.cafegory.cafegory.cafe.domain.CafeId;
import com.cafegory.cafegory.member.domain.MemberId;
import com.cafegory.cafegory.study.domain.StudyContent;
import com.cafegory.cafegory.study.domain.StudyId;

import java.time.LocalDateTime;

public interface StudyRepository {

	StudyId save(StudyContent content, CafeId cafeId, MemberId memberId);

	void deleteWithCascade(StudyId studyId, MemberId memberId, LocalDateTime now);
}
