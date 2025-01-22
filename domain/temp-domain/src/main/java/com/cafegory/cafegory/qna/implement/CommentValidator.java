package com.cafegory.cafegory.qna.implement;

import com.cafegory.cafegory.exception.CafegoryException;
import com.cafegory.cafegory.member.domain.MemberId;
import com.cafegory.cafegory.qna.domain.Comment;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import static com.cafegory.cafegory.exception.ExceptionType.*;

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
