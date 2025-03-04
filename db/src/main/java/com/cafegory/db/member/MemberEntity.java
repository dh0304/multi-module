package com.cafegory.db.member;

import com.cafegory.db.BaseEntity;
import com.cafegory.domain.common.DateAudit;
import com.cafegory.domain.member.domain.*;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Where;


@Builder
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Setter
@Where(clause = "deleted_date IS NULL")
@Table(name = "member")
public class MemberEntity extends BaseEntity {

	@Id
	@GeneratedValue
	@Column(name = "member_id")
	private Long id;

	@Enumerated(EnumType.STRING)
	private Role role;

	private String nickname;

	@Column(unique = true)
	private String email;

	private String profileUrl;
	private String bio;
	private int participationCount;

	@Enumerated(EnumType.STRING)
	private BeverageSize beverageSize;

	private String refreshToken;

	public MemberEntity(Long id) {
		this.id = id;
	}

	public MemberEntity(Member member) {
		this.role = member.getRole();
		this.nickname = member.getContent().getNickname();
		this.email = member.getEmail();
		this.profileUrl = member.getContent().getImgUrl();
		this.bio = member.getBio();
		this.beverageSize = member.getBeverageSize();
		this.refreshToken = member.getRefreshToken();
	}

	public Member toMember() {
		return Member.builder()
			.id(new MemberId(this.id))
			.content(
				MemberContent.builder()
					.nickname(this.nickname)
					.imgUrl(this.profileUrl)
					.build()
			)
			.role(this.role)
			.email(this.email)
			.bio(this.bio)
			.beverageSize(this.beverageSize)
			.dateAudit(
				DateAudit.builder()
					.createdDate(this.getCreatedDate())
					.modifiedDate(this.getLastModifiedDate())
					.build()
			)
			.refreshToken(this.refreshToken)
			.build();
	}
}
