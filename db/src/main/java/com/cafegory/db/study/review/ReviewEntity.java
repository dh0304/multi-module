package com.cafegory.db.study.review;

import com.cafegory.db.BaseEntity;
import com.cafegory.db.cafe.cafe.CafeEntity;
import com.cafegory.domain.cafe.domain.Cafe;
import com.cafegory.domain.cafe.domain.CafeId;
import com.cafegory.domain.common.DateAudit;
import com.cafegory.db.member.MemberEntity;
import com.cafegory.domain.study.domain.Review;
import com.cafegory.domain.study.domain.ReviewId;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Where;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Builder
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Setter
@Where(clause = "deleted_date IS NULL")
@Table(name = "review")
public class ReviewEntity extends BaseEntity {

	@Id
	@GeneratedValue
	@Column(name = "review_id")
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cafe_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
	private CafeEntity cafe;

	//TODO 멤버 대신 StudyMember가 필요할 것 같다.
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
	private MemberEntity member;

	@Builder.Default
	@OneToMany(mappedBy = "review")
	private List<ReviewCafeTagEntity> reviewCafeTag = new ArrayList<>();

	public Review toReview() {
		return Review.builder()
			.id(new ReviewId(this.id))
			.tags(
				reviewCafeTag.stream()
					.map(tag -> tag.getCafeTag().getType())
					.collect(Collectors.toList())
			)
			.cafe(
				Cafe.builder()
					.id(new CafeId(cafe.getId()))
					.name(cafe.getName())
					.imgUrl(cafe.getMainImageUrl())
					.build()
			)
			.dateAudit(
				DateAudit.builder()
					.createdDate(this.getCreatedDate())
					.modifiedDate(this.getLastModifiedDate())
					.build()
			)
			.build();
	}
}
