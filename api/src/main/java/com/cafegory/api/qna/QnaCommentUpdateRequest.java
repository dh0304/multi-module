package com.cafegory.api.qna;

import com.cafegory.domain.qna.domain.CommentContent;
import com.cafegory.domain.qna.domain.CommentId;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class QnaCommentUpdateRequest {

	private Long commentId;
	@NotBlank
	private String content;

	public CommentContent toCommentContent() {
		return CommentContent.builder()
			.content(content)
			.build();
	}

	public CommentId toCommentId() {
		return new CommentId(commentId);
	}
}
