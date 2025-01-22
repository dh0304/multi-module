package com.cafegory.cafegory.qna.repository;

import com.cafegory.cafegory.member.domain.MemberId;
import com.cafegory.cafegory.qna.domain.CommentContent;
import com.cafegory.cafegory.qna.domain.CommentId;
import com.cafegory.cafegory.qna.domain.ParentCommentId;
import com.cafegory.cafegory.study.domain.StudyId;

import java.time.LocalDateTime;

public interface CommentRepository {

	CommentId saveRootComment(CommentContent content, StudyId studyId, MemberId memberId);

	CommentId saveSubComment(CommentContent comment, ParentCommentId parentCommentId, StudyId studyId,
							 MemberId memberId);

	void edit(CommentContent content, CommentId commentId);

	void remove(CommentId commentId, LocalDateTime now);
}
