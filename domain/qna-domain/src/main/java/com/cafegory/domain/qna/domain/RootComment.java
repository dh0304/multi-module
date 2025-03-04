package com.cafegory.domain.qna.domain;

import com.cafegory.domain.common.DateAudit;
import com.cafegory.domain.member.domain.MemberIdentity;
import lombok.Builder;
import lombok.Getter;
import com.cafegory.domain.study.domain.StudyId;

@Getter
@Builder
public class RootComment {

	private CommentId id;
	private CommentContent commentContent;
	private StudyId studyId;
	private MemberIdentity author;

	private DateAudit date;

	public boolean isAuthor(Long memberId) {
		return this.author.isSameMember(memberId);
	}

	public boolean hasId(CommentId id) {
		return this.id.isSameId(id);
	}

	public boolean hasStudyId(StudyId id) {
		return this.studyId.isSameId(id);
	}

	public String getContent() {
		return this.commentContent.getContent();
	}
}
