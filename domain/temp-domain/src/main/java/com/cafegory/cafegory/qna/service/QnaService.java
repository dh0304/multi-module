package com.cafegory.cafegory.qna.service;

import com.cafegory.cafegory.exception.CafegoryException;
import com.cafegory.cafegory.qna.domain.CommentContent;
import lombok.RequiredArgsConstructor;
import com.cafegory.cafegory.member.domain.MemberId;
import org.springframework.stereotype.Service;
import com.cafegory.cafegory.qna.domain.Comment;
import com.cafegory.cafegory.qna.domain.CommentId;
import com.cafegory.cafegory.qna.domain.ParentCommentId;
import com.cafegory.cafegory.qna.implement.CommentEditor;
import com.cafegory.cafegory.qna.implement.CommentReader;
import com.cafegory.cafegory.qna.implement.CommentValidator;
import com.cafegory.cafegory.study.domain.StudyId;

import java.time.LocalDateTime;

import static com.cafegory.cafegory.exception.ExceptionType.CAFE_STUDY_COMMENT_HAS_REPLY;

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