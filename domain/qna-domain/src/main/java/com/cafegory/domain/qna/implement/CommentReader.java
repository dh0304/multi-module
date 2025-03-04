package com.cafegory.domain.qna.implement;

import com.cafegory.domain.common.exception.CafegoryException;
import com.cafegory.domain.qna.domain.Comment;
import com.cafegory.domain.qna.domain.CommentId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import com.cafegory.domain.qna.repository.CommentQueryRepository;

import static com.cafegory.domain.common.exception.ExceptionType.STUDY_ONCE_COMMENT_NOT_FOUND;

@Component
@RequiredArgsConstructor
public class CommentReader {

	private final CommentQueryRepository commentQueryRepository;

	public Comment read(CommentId commentId) {
		return commentQueryRepository.findWithMember(commentId)
			.orElseThrow(() -> new CafegoryException(STUDY_ONCE_COMMENT_NOT_FOUND));
	}

	public boolean existsReplies(CommentId commentId) {
		return commentQueryRepository.hasReplies(commentId);
	}
}
