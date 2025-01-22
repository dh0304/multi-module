package qna.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import qna.domain.Comment;
import qna.domain.CommentId;
import qna.implement.CommentReader;

@Service
@RequiredArgsConstructor
public class QnaQueryService {

	private final CommentReader commentReader;

	public Comment getComment(CommentId commentId) {
		return commentReader.read(commentId);
	}
}
