package qna.repository;

import qna.domain.Comment;
import qna.domain.CommentId;

import java.util.Optional;

public interface CommentQueryRepository {

	Optional<Comment> findWithMember(CommentId commentId);

	boolean hasReplies(CommentId commentId);
}
