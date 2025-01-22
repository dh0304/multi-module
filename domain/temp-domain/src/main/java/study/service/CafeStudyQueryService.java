package study.service;

import common.SliceResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import study.domain.Study;
import study.domain.StudyId;
import study.implement.StudyReader;
import study.repository.CafeStudySearchListRequest;
import study.repository.StudySearchListResponse;

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
