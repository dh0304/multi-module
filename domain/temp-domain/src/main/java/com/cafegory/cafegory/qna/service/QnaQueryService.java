package com.cafegory.cafegory.qna.service;

import com.cafegory.cafegory.qna.domain.Comment;
import com.cafegory.cafegory.qna.domain.CommentId;
import com.cafegory.cafegory.qna.implement.CommentReader;
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
