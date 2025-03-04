package com.cafegory.db.qna;

import com.cafegory.domain.qna.domain.Comment;
import com.cafegory.domain.qna.domain.CommentId;
import com.cafegory.domain.qna.repository.CommentQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CommentQueryRepositoryImpl implements CommentQueryRepository {

	private final CommentJpaRepository commentRepository;

	@Override
	@Transactional
	public Optional<Comment> findWithMember(CommentId commentId) {
		return commentRepository.findWithMember(commentId.getId())
			.map(CafeStudyCommentEntity::toComment);
	}

	@Override
	public boolean hasReplies(CommentId commentId) {
		return commentRepository.existsByParentComment_Id(commentId.getId());
	}
}
