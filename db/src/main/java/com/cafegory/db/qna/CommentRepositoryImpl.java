package com.cafegory.db.qna;

import com.cafegory.domain.member.domain.MemberId;
import com.cafegory.domain.qna.domain.CommentContent;
import com.cafegory.domain.qna.domain.CommentId;
import com.cafegory.domain.qna.domain.ParentCommentId;
import com.cafegory.domain.qna.repository.CommentRepository;
import com.cafegory.domain.study.domain.StudyId;
import com.cafegory.domain.study.domain.StudyRole;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Repository
@RequiredArgsConstructor
public class CommentRepositoryImpl implements CommentRepository {

	private final CommentJpaRepository commentJpaRepository;

	@Override
	public CommentId saveRootComment(CommentContent content, StudyId studyId, MemberId memberId) {
		CafeStudyCommentEntity commentEntity = commentJpaRepository.save(
			CafeStudyCommentEntity.createRootComment(content, memberId, studyId, StudyRole.MEMBER));

		return new CommentId(commentEntity.getId());
	}

	@Override
	public CommentId saveSubComment(
			CommentContent content, ParentCommentId parentCommentId, StudyId studyId, MemberId memberId
	) {
		//TODO STUDYROLE 수정 필요
		CafeStudyCommentEntity commentEntity = commentJpaRepository.save(
			CafeStudyCommentEntity.createSubComment(content, parentCommentId, memberId, studyId, StudyRole.MEMBER));

		return new CommentId(commentEntity.getId());
	}

	@Override
	@Transactional
	public void edit(CommentContent content, CommentId commentId) {
		commentJpaRepository.findById(commentId.getId())
			.orElseThrow(() -> new IllegalArgumentException("comment가 존재하지 않습니다."))
			.changeContent(content.getContent());
	}

	@Override
	@Transactional
	public void remove(CommentId commentId, LocalDateTime now) {
		commentJpaRepository.findById(commentId.getId())
			.orElseThrow(() -> new IllegalArgumentException("comment가 존재하지 않습니다."))
			.softDelete(now);
	}
}
