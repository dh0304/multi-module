package com.cafegory.cafegory.qna.implement;

import com.cafegory.cafegory.exception.CafegoryException;
import com.cafegory.cafegory.qna.domain.Comment;
import com.cafegory.cafegory.qna.domain.CommentId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import com.cafegory.cafegory.qna.repository.CommentQueryRepository;

import static com.cafegory.cafegory.exception.ExceptionType.*;

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
