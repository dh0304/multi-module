package com.cafegory.domain.qna.service;

import com.cafegory.domain.common.exception.CafegoryException;
import com.cafegory.domain.member.domain.MemberId;
import com.cafegory.domain.qna.domain.CommentContent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.cafegory.domain.qna.domain.Comment;
import com.cafegory.domain.qna.domain.CommentId;
import com.cafegory.domain.qna.domain.ParentCommentId;
import com.cafegory.domain.qna.implement.CommentEditor;
import com.cafegory.domain.qna.implement.CommentReader;
import com.cafegory.domain.qna.implement.CommentValidator;
import com.cafegory.domain.study.domain.StudyId;

import java.time.LocalDateTime;

import static com.cafegory.domain.common.exception.ExceptionType.CAFE_STUDY_COMMENT_HAS_REPLY;

@Service
@RequiredArgsConstructor
public class QnaService {

	private final CommentEditor commentEditor;
	private final CommentReader commentReader;
	private final CommentValidator commentValidator;

	public CommentId leaveComment(
            CommentContent content, ParentCommentId parentCommentId, StudyId studyId, MemberId memberId
	) {
		if (parentCommentId.isNull()) {
			return commentEditor.saveRootComment(content, studyId, memberId);
		}
		return commentEditor.saveSubComment(content, parentCommentId, studyId, memberId);
	}

	public void editComment(CommentContent content, CommentId commentId, MemberId memberId) {
		Comment comment = commentReader.read(commentId);
		commentValidator.validateCommentAuthor(comment, memberId);
		validateNoReplies(commentId);

		commentEditor.edit(content, commentId);
	}

	public void removeComment(CommentId commentId, MemberId memberId, LocalDateTime now) {
		Comment comment = commentReader.read(commentId);
		commentValidator.validateCommentAuthor(comment, memberId);
		validateNoReplies(commentId);

		commentEditor.remove(commentId, now);
	}

	private void validateNoReplies(CommentId commentId) {
		if (commentReader.existsReplies(commentId)) {
			throw new CafegoryException(CAFE_STUDY_COMMENT_HAS_REPLY);
		}
	}
}