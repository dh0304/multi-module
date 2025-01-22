package com.cafegory.cafegory.qna.repository;

import com.cafegory.cafegory.qna.domain.Comment;
import com.cafegory.cafegory.qna.domain.CommentId;

import java.util.Optional;

public interface CommentQueryRepository {

	Optional<Comment> findWithMember(CommentId commentId);

	boolean hasReplies(CommentId commentId);
}
