package com.cafegory.domain.qna.service;

import com.cafegory.db.cafe.cafe.CafeEntity;
import com.cafegory.db.member.MemberEntity;
import com.cafegory.db.qna.CafeStudyCommentEntity;
import com.cafegory.db.study.study.CafeStudyEntity;
import com.cafegory.domain.common.exception.CafegoryException;
import com.cafegory.domain.common.time.TimeUtil;
import com.cafegory.domain.config.ServiceTest;
import com.cafegory.domain.member.domain.MemberId;
import com.cafegory.domain.qna.domain.CommentContent;
import com.cafegory.domain.qna.domain.CommentId;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static com.cafegory.db.testfixtures.persister.cafe.CafeContextPersister.aCafe;
import static com.cafegory.db.testfixtures.persister.member.MemberPersister.aMember;
import static com.cafegory.db.testfixtures.persister.qna.CommentPersister.aComment;
import static com.cafegory.db.testfixtures.persister.study.StudyConextPersister.aStudy;
import static com.cafegory.domain.common.exception.ExceptionType.CAFE_STUDY_COMMENT_HAS_REPLY;
import static com.cafegory.domain.qna.testfixtures.builder.CommentContentBuilder.aCommentContent;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class QnaServiceTest extends ServiceTest {

	@Autowired
	private QnaService sut;

	@Autowired
	private TimeUtil timeUtil;

	@Test
	@DisplayName("답변이 작성된 댓글은 수정할 수 없다.")
	void can_not_edit_comment_WithReplies() {
		//given
		CafeEntity cafe = aCafe().persist();

		MemberEntity coordinator = aMember().asCoordinator().persist();
		MemberEntity member = aMember().asParticipant().persist();

		CafeStudyEntity study = aStudy().withCafe(cafe).withMember(member).persist();
		CafeStudyCommentEntity rootComment = aComment().withStudy(study).withMember(member).persist();
		aComment().replyTo(rootComment).withStudy(study).withCoordinator(coordinator).persist();
		//when & then
		assertThatThrownBy(() -> sut.editComment(
			aCommentContent().withContent("변경된 댓글 내용").build(),
			new CommentId(rootComment.getId()), new MemberId(member.getId())))
			.isInstanceOf(CafegoryException.class)
			.hasMessage(CAFE_STUDY_COMMENT_HAS_REPLY.getErrorMessage());
	}

	private CommentContent createCommentContent(String content) {
		return CommentContent.builder()
			.content(content)
			.build();
	}

	@Test
	@DisplayName("답변이 작성된 댓글은 삭제할 수 없다.")
	void can_not_remove_comment_WithReplies() {
		//given
		CafeEntity cafe = aCafe().persist();

		MemberEntity coordinator = aMember().asCoordinator().persist();
		MemberEntity member = aMember().asParticipant().persist();

		CafeStudyEntity study = aStudy().withCafe(cafe).withMember(member).persist();
		CafeStudyCommentEntity rootComment = aComment().withStudy(study).withMember(member).persist();
		aComment().replyTo(rootComment).withStudy(study).withCoordinator(coordinator).persist();
		//when & then
		assertThatThrownBy(() -> sut.removeComment(
			new CommentId(rootComment.getId()), new MemberId(member.getId()), timeUtil.now()))
			.isInstanceOf(CafegoryException.class)
			.hasMessage(CAFE_STUDY_COMMENT_HAS_REPLY.getErrorMessage());
	}
}
