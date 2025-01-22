package qna.implement;

import lombok.RequiredArgsConstructor;
import member.domain.MemberId;
import org.springframework.stereotype.Component;
import qna.domain.CommentContent;
import qna.domain.CommentId;
import qna.domain.ParentCommentId;
import qna.repository.CommentRepository;
import study.domain.StudyId;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class CommentEditor {

	private final CommentRepository commentRepository;
	private final CommentValidator commentValidator;

	public CommentId saveRootComment(CommentContent content, StudyId studyId, MemberId memberId) {
		commentValidator.validateContentNotBlank(content.getContent());
		return commentRepository.saveRootComment(content, studyId, memberId);
	}

	public CommentId saveSubComment(
			CommentContent content, ParentCommentId parentCommentId, StudyId studyId, MemberId memberId
	) {
		commentValidator.validateContentNotBlank(content.getContent());
		return commentRepository.saveSubComment(content, parentCommentId, studyId, memberId);
	}

	public void edit(CommentContent content, CommentId commentId) {
		commentValidator.validateContentNotBlank(content.getContent());
		commentRepository.edit(content, commentId);
	}

	public void remove(CommentId commentId, LocalDateTime now) {
		commentRepository.remove(commentId, now);
	}
}
