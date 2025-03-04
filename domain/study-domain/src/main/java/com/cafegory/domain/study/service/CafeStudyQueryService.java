package com.cafegory.domain.study.service;

import com.cafegory.domain.common.SliceResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.cafegory.domain.study.domain.Study;
import com.cafegory.domain.study.domain.StudyId;
import com.cafegory.domain.study.implement.StudyReader;
import com.cafegory.domain.study.dto.CafeStudySearchListRequest;
import com.cafegory.domain.study.dto.StudySearchListResponse;

@Service
@RequiredArgsConstructor
public class CafeStudyQueryService {

	private final StudyReader studyReader;

	public SliceResponse<StudySearchListResponse> searchCafeStudiesByDynamicFilter(CafeStudySearchListRequest request) {
		return studyReader.searchCafeStudies(request);
	}

	public Study getStudy(StudyId studyId) {
		return studyReader.read(studyId);
	}

	public int getViewCount(StudyId studyId) {
		return studyReader.readViewCountBy(studyId);
	}
}
