package qna.implement;

import exception.CafegoryException;
import exception.ExceptionType;
import member.domain.MemberId;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import qna.domain.Comment;

import static exception.ExceptionType.*;

@Component
public class CommentValidator {

	public void validateContentNotBlank(String content) {
		if (!StringUtils.hasText(content)) {
			throw new CafegoryException(CAFE_STUDY_COMMENT_CONTENT_NOT_BLANK);
		}
	}

	public void validateCommentAuthor(Comment comment, MemberId memberId) {
		if (!comment.isAuthor(memberId.getId())) {
			throw new CafegoryException(CAFE_STUDY_COMMENT_PERMISSION_DENIED);
		}
	}
}
