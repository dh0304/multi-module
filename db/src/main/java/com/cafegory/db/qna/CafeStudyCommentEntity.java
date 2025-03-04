package com.cafegory.db.qna;

import com.cafegory.db.BaseEntity;
import com.cafegory.db.member.MemberEntity;
import com.cafegory.domain.common.DateAudit;
import com.cafegory.domain.member.domain.MemberId;
import com.cafegory.domain.member.domain.MemberIdentity;
import com.cafegory.domain.qna.domain.Comment;
import com.cafegory.domain.qna.domain.CommentContent;
import com.cafegory.domain.qna.domain.CommentId;
import com.cafegory.domain.qna.domain.ParentCommentId;
import com.cafegory.domain.study.domain.StudyId;
import com.cafegory.domain.study.domain.StudyRole;
import com.cafegory.db.study.study.CafeStudyEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Where;

import java.util.ArrayList;
import java.util.List;

@Builder
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Setter
@Where(clause = "deleted_date IS NULL")
@Table(name = "cafe_study_comment")
public class CafeStudyCommentEntity extends BaseEntity {

	@Id
	@GeneratedValue
	@Column(name = "cafe_study_comment_id")
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "author_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
	private MemberEntity author;

	private StudyRole studyRole;
	private String content;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parent_comment_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
	private CafeStudyCommentEntity parentComment;

	@Builder.Default
	@OneToMany(mappedBy = "parentComment")
	private List<CafeStudyCommentEntity> childrenComments = new ArrayList<>();

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cafe_study_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
	private CafeStudyEntity cafeStudy;

	public CafeStudyCommentEntity(Long id) {
		this.id = id;
	}

	public Comment toComment() {
		return Comment.builder()
			.id(new CommentId(this.id))
			.commentContent(
				CommentContent.builder()
					.content(this.content)
					.build()
			)
			.author(
				MemberIdentity.builder()
					.id(this.author.getId())
					.nickname(this.author.getNickname())
					.build()
			)
			.studyId(new StudyId(this.cafeStudy.getId()))
			.date(
				DateAudit.builder()
					.createdDate(getCreatedDate())
					.modifiedDate(getLastModifiedDate())
					.build()
			)
			.build();
	}

	public static CafeStudyCommentEntity createRootComment(
			CommentContent content, MemberId authorId, StudyId studyId, StudyRole studyRole
	) {
		return CafeStudyCommentEntity.builder()
			.author(new MemberEntity(authorId.getId()))
			.content(content.getContent())
			.studyRole(studyRole)
			.cafeStudy(new CafeStudyEntity(studyId.getId()))
			.build();
	}

	public static CafeStudyCommentEntity createSubComment(
			CommentContent content, ParentCommentId parentCommentId, MemberId authorId, StudyId studyId, StudyRole studyRole
	) {
		return CafeStudyCommentEntity.builder()
			.author(new MemberEntity(authorId.getId()))
			.content(content.getContent())
			.parentComment(new CafeStudyCommentEntity(parentCommentId.getId()))
			.studyRole(studyRole)
			.cafeStudy(new CafeStudyEntity(studyId.getId()))
			.build();
	}

	public void changeContent(String content) {
		this.content = content;
	}

	public boolean hasParentComment() {
		return this.parentComment != null;
	}
}
