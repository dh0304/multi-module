package com.cafegory.api.qna;

import com.cafegory.domain.common.time.TimeUtil;
import com.cafegory.domain.member.domain.MemberId;
import com.cafegory.domain.qna.domain.Comment;
import com.cafegory.domain.qna.domain.CommentId;
import com.cafegory.domain.qna.service.QnaQueryService;
import com.cafegory.domain.qna.service.QnaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/qna")
public class QnaController {

	private final QnaService qnaService;
	private final QnaQueryService qnaQueryService;

	private final TimeUtil timeUtil;

	@PostMapping("/comments")
	public ResponseEntity<QnaCommentSaveResponse> leaveComment(
		@Validated @RequestBody QnaCommentSaveRequest request, @AuthenticationPrincipal UserDetails userDetails
	) {
		MemberId memberId = new MemberId(Long.parseLong(userDetails.getUsername()));

		CommentId commentId = qnaService.leaveComment(request.toCommentContent(), request.toParentCommentId(),
			request.toStudyId(), memberId);
		Comment comment = qnaQueryService.getComment(commentId);

		QnaCommentSaveResponse response = QnaCommentSaveResponse.from(comment);
		return ResponseEntity.ok(response);
	}

	@PatchMapping("/comments/{commentId}")
	public ResponseEntity<QnaCommentUpdateResponse> editComment(
		@Validated @RequestBody QnaCommentUpdateRequest request,
		@AuthenticationPrincipal UserDetails userDetails
	) {
		MemberId memberId = new MemberId(Long.parseLong(userDetails.getUsername()));

		qnaService.editComment(request.toCommentContent(), request.toCommentId(), memberId);
		Comment comment = qnaQueryService.getComment(request.toCommentId());

		QnaCommentUpdateResponse response = QnaCommentUpdateResponse.from(comment);
		return ResponseEntity.ok(response);
	}

	@DeleteMapping("/comments/{commentId}")
	public ResponseEntity<Void> removeComment(
		@PathVariable Long commentId,
		@AuthenticationPrincipal UserDetails userDetails
	) {
		MemberId memberId = new MemberId(Long.parseLong(userDetails.getUsername()));
		qnaService.removeComment(new CommentId(commentId), memberId, timeUtil.now());

		return ResponseEntity.ok().build();
	}
}
