package qna.implement;

import exception.CafegoryException;
import exception.ExceptionType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import qna.domain.Comment;
import qna.domain.CommentId;
import qna.repository.CommentQueryRepository;

import static exception.ExceptionType.*;

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
