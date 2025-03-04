package com.cafegory.api.study;

import com.cafegory.domain.cafe.domain.Cafe;
import com.cafegory.domain.cafe.service.CafeQueryService;
import com.cafegory.domain.common.SliceResponse;
import com.cafegory.domain.common.time.TimeUtil;
import com.cafegory.domain.member.domain.MemberId;
import com.cafegory.domain.study.domain.Study;
import com.cafegory.domain.study.domain.StudyId;
import com.cafegory.domain.study.dto.CafeStudySearchListRequest;
import com.cafegory.domain.study.dto.StudySearchListResponse;
import com.cafegory.domain.study.service.CafeStudyQueryService;
import com.cafegory.domain.study.service.CafeStudyService;
import com.cafegory.domain.study.service.StudyMemberQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cafe-studies")
public class CafeStudyController {

	private final CafeStudyService cafeStudyService;
	private final CafeStudyQueryService cafeStudyQueryService;

	private final StudyMemberQueryService studyMemberQueryService;

	private final CafeQueryService cafeQueryService;

	private final TimeUtil timeUtil;

	@GetMapping("/{studyId}")
	public ResponseEntity<CafeStudyDetailResponse> getCafeStudyDetail(@PathVariable Long studyId) {
		StudyId id = new StudyId(studyId);

		Study study = cafeStudyQueryService.getStudy(id);
		int viewCount = cafeStudyQueryService.getViewCount(id);
		int participantCount = studyMemberQueryService.getParticipantCount(id);
		Cafe cafe = cafeQueryService.getCafe(study.getCafeId());

		CafeStudyDetailResponse response = CafeStudyDetailResponse.of(cafe, study, viewCount, participantCount);
		return ResponseEntity.ok(response);
	}

	@GetMapping
	public ResponseEntity<SliceResponse<StudySearchListResponse>> searchCafeStudies(
		@Validated @ModelAttribute CafeStudySearchListRequest request) {
		SliceResponse<StudySearchListResponse> response = cafeStudyQueryService.searchCafeStudiesByDynamicFilter(
			request);
		return ResponseEntity.ok(response);
	}

	@PostMapping
	public ResponseEntity<CafeStudyCreateResponse> create(
		@RequestBody @Validated CafeStudyCreateRequest request,
		@AuthenticationPrincipal UserDetails userDetails) {
		MemberId memberId = new MemberId(Long.parseLong(userDetails.getUsername()));
		StudyId studyId = cafeStudyService.createStudy(
			memberId, timeUtil.now(), request.toStudyContent(), request.toCafeId());
		Study study = cafeStudyQueryService.getStudy(studyId);

		CafeStudyCreateResponse response = CafeStudyCreateResponse.from(study);
		return ResponseEntity.ok(response);
	}

	@DeleteMapping("/{cafeStudyId:[0-9]+}")
	public ResponseEntity<Void> delete(@PathVariable Long cafeStudyId,
		@AuthenticationPrincipal UserDetails userDetails) {
		MemberId memberId = new MemberId(Long.parseLong(userDetails.getUsername()));
		cafeStudyService.deleteStudy(memberId, new StudyId(cafeStudyId), timeUtil.now());

		return ResponseEntity.ok().build();
	}
}
