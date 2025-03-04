package com.cafegory.domain.qna.implement;

import com.cafegory.domain.common.exception.CafegoryException;
import com.cafegory.domain.member.domain.MemberId;
import com.cafegory.domain.member.domain.MemberIdentity;
import com.cafegory.domain.qna.domain.Comment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static com.cafegory.domain.common.exception.ExceptionType.CAFE_STUDY_COMMENT_CONTENT_NOT_BLANK;
import static com.cafegory.domain.common.exception.ExceptionType.CAFE_STUDY_COMMENT_PERMISSION_DENIED;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class CommentValidatorTest {

	private CommentValidator sut = new CommentValidator();

	@Test
	@DisplayName("댓글 문자열 검증")
	void validate_not_blank1() {
		assertDoesNotThrow(() -> sut.validateContentNotBlank("텍스트"));
	}

	@ParameterizedTest
	@ValueSource(strings = {"", " "})
	@DisplayName("댓글, 빈 문자열 또는 공백 검증")
	void validate_not_blank2(String value) {
		assertThatThrownBy(() -> sut.validateContentNotBlank(value))
			.isInstanceOf(CafegoryException.class)
			.hasMessage(CAFE_STUDY_COMMENT_CONTENT_NOT_BLANK.getErrorMessage());
	}

	@Test
	@DisplayName("댓글 작성자와 수정을 요청한 사용자가 일치한다.")
	void success_validate_comment_author_() {
		Comment comment = Comment.builder()
			.author(MemberIdentity.builder().id(1L).build())
			.build();

		assertDoesNotThrow(() -> sut.validateCommentAuthor(comment, new MemberId(1L)));
	}

	@Test
	@DisplayName("댓글 작성자와 수정을 요청한 사용자가 일치하지 않는다.")
	void fail_validate_comment_author2() {
		Comment comment = Comment.builder()
			.author(MemberIdentity.builder().id(1L).build())
			.build();
		assertThatThrownBy(() -> sut.validateCommentAuthor(comment, new MemberId(2L)))
			.isInstanceOf(CafegoryException.class)
			.hasMessage(CAFE_STUDY_COMMENT_PERMISSION_DENIED.getErrorMessage());
	}
}