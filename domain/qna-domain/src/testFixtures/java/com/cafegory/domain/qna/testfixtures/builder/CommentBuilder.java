package com.cafegory.domain.qna.testfixtures.builder;

import com.cafegory.domain.common.DateAudit;
import com.cafegory.domain.common.testfixtures.builder.DateAuditBuilder;
import com.cafegory.domain.member.domain.MemberIdentity;
import com.cafegory.domain.member.testfixtures.builder.MemberIdentityBuilder;
import com.cafegory.domain.qna.domain.Comment;
import com.cafegory.domain.qna.domain.CommentContent;
import com.cafegory.domain.qna.domain.CommentId;
import com.cafegory.domain.qna.domain.ParentCommentId;
import com.cafegory.domain.study.domain.StudyId;

import static com.cafegory.domain.common.testfixtures.builder.DateAuditBuilder.aDateAudit;
import static com.cafegory.domain.member.testfixtures.builder.MemberIdentityBuilder.aMemberIdentity;

public class CommentBuilder {

    private CommentId id = new CommentId(1L);
    private CommentContent commentContent = CommentContentBuilder.aCommentContent().build();
    private ParentCommentId parentCommentId;
    private StudyId studyId = new StudyId(1L);
    private MemberIdentity author = aMemberIdentity().build();
    private DateAudit date = aDateAudit().build();

    private CommentBuilder() {}

    private CommentBuilder(CommentBuilder copy) {
        this.id = copy.id;
        this.commentContent = copy.commentContent;
        this.parentCommentId = copy.parentCommentId;
        this.studyId = copy.studyId;
        this.author = copy.author;
        this.date = copy.date;
    }

    public CommentBuilder but() {
        return new CommentBuilder(this);
    }

    public static CommentBuilder aComment() {
        return new CommentBuilder();
    }

    public CommentBuilder withId(Long Id) {
        this.id = new CommentId(Id);
        return this;
    }

    public CommentBuilder with(CommentContentBuilder commentContentBuilder) {
        this.commentContent = commentContentBuilder.build();
        return this;
    }

    public CommentBuilder withParentCommentId(Long parentCommentId) {
        this.parentCommentId = new ParentCommentId(parentCommentId);
        return this;
    }

    public CommentBuilder withStudyId(Long studyId) {
        this.studyId = new StudyId(studyId);
        return this;
    }

    public CommentBuilder with(MemberIdentityBuilder memberIdentityBuilder) {
        this.author = memberIdentityBuilder.build();
        return this;
    }

    public CommentBuilder with(DateAuditBuilder dateAuditBuilder) {
        this.date = dateAuditBuilder.build();
        return this;
    }

    public Comment build() {
        return Comment.builder()
                .id(this.id)
                .commentContent(this.commentContent)
                .parentCommentId(this.parentCommentId)
                .studyId(this.studyId)
                .author(this.author)
                .date(this.date)
                .build();
    }
}
