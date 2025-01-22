package qna.service;

import exception.CafegoryException;
import lombok.RequiredArgsConstructor;
import member.domain.MemberId;
import org.springframework.stereotype.Service;
import qna.domain.Comment;
import qna.domain.CommentContent;
import qna.domain.CommentId;
import qna.domain.ParentCommentId;
import qna.implement.CommentEditor;
import qna.implement.CommentReader;
import qna.implement.CommentValidator;
import study.domain.StudyId;

import java.time.LocalDateTime;

import static exception.ExceptionType.CAFE_STUDY_COMMENT_HAS_REPLY;

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