package com.cafegory.cafegory.qna.domain;

import com.cafegory.cafegory.common.DateAudit;
import lombok.Builder;
import lombok.Getter;
import com.cafegory.cafegory.member.domain.MemberIdentity;
import com.cafegory.cafegory.study.domain.StudyId;

@Getter
@Builder
public class ChildComment {

	private CommentId id;
	private CommentContent commentContent;
	private ParentCommentId parentCommentId;
	private StudyId studyId;
	private MemberIdentity author;

	private DateAudit date;

	public boolean isAuthor(Long memberId) {
		return this.author.isSameMember(memberId);
	}

	public String getContent() {
		return this.commentContent.getContent();
	}

	public boolean hasId(CommentId id) {
		return this.id.isSameId(id);
	}

	public boolean hasParentCommentId(ParentCommentId id) {
		return parentCommentId.isSameId(id);
	}

	public boolean hasStudyId(StudyId id) {
		return this.studyId.isSameId(id);
	}
}
