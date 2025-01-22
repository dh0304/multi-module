package com.cafegory.cafegory.study.service;

import com.cafegory.cafegory.common.SliceResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.cafegory.cafegory.study.domain.Study;
import com.cafegory.cafegory.study.domain.StudyId;
import com.cafegory.cafegory.study.implement.StudyReader;
import com.cafegory.cafegory.study.repository.CafeStudySearchListRequest;
import com.cafegory.cafegory.study.repository.StudySearchListResponse;

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
