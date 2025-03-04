package com.cafegory.db.study.review;

import com.cafegory.db.BaseEntity;
import com.cafegory.db.cafe.tag.CafeTagEntity;
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
@Table(name = "review_cafe_tag")
public class ReviewCafeTagEntity extends BaseEntity {

	@Id
	@GeneratedValue
	@Column(name = "review_cafe_tag_id")
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "review_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
	private ReviewEntity review;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cafe_tag_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
	private CafeTagEntity cafeTag;
}
