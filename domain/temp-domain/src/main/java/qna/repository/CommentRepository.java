package qna.repository;

import member.domain.MemberId;
import qna.domain.CommentContent;
import qna.domain.CommentId;
import qna.domain.ParentCommentId;
import study.domain.StudyId;

import java.time.LocalDateTime;

public interface CommentRepository {

	CommentId saveRootComment(CommentContent content, StudyId studyId, MemberId memberId);

	CommentId saveSubComment(CommentContent comment, ParentCommentId parentCommentId, StudyId studyId,
							 MemberId memberId);

	void edit(CommentContent content, CommentId commentId);

	void remove(CommentId commentId, LocalDateTime now);
}
