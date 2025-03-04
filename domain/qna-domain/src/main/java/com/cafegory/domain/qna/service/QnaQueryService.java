package com.cafegory.domain.qna.service;

import com.cafegory.domain.qna.domain.Comment;
import com.cafegory.domain.qna.domain.CommentId;
import com.cafegory.domain.qna.implement.CommentReader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QnaQueryService {

	private final CommentReader commentReader;

	public Comment getComment(CommentId commentId) {
		return commentReader.read(commentId);
	}
}
