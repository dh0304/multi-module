package com.cafegory.domain.qna.repository;

import com.cafegory.domain.member.domain.MemberId;
import com.cafegory.domain.qna.domain.CommentContent;
import com.cafegory.domain.qna.domain.CommentId;
import com.cafegory.domain.qna.domain.ParentCommentId;
import com.cafegory.domain.study.domain.StudyId;

import java.time.LocalDateTime;

public interface CommentRepository {

	CommentId saveRootComment(CommentContent content, StudyId studyId, MemberId memberId);

	CommentId saveSubComment(CommentContent comment, ParentCommentId parentCommentId, StudyId studyId,
                             MemberId memberId);

	void edit(CommentContent content, CommentId commentId);

	void remove(CommentId commentId, LocalDateTime now);
}
