package com.cafegory.domain.qna.implement;

import com.cafegory.domain.common.exception.CafegoryException;
import com.cafegory.domain.member.domain.MemberId;
import com.cafegory.domain.qna.domain.Comment;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import static com.cafegory.domain.common.exception.ExceptionType.CAFE_STUDY_COMMENT_CONTENT_NOT_BLANK;
import static com.cafegory.domain.common.exception.ExceptionType.CAFE_STUDY_COMMENT_PERMISSION_DENIED;

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
