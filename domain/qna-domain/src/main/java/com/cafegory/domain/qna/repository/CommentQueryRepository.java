package com.cafegory.domain.qna.repository;

import com.cafegory.domain.qna.domain.Comment;
import com.cafegory.domain.qna.domain.CommentId;

import java.util.Optional;

public interface CommentQueryRepository {

	Optional<Comment> findWithMember(CommentId commentId);

	boolean hasReplies(CommentId commentId);
}
