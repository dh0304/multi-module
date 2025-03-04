package com.cafegory.db.cafe.tag;

import com.cafegory.db.BaseEntity;
import com.cafegory.db.cafe.cafe.CafeEntity;
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
@Table(name = "cafe_cafe_tag")
public class CafeCafeTagEntity extends BaseEntity {

	@Id
	@GeneratedValue
	@Column(name = "cafe_cafe_tag_id")
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cafe_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
	private CafeEntity cafe;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cafe_tag_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
	private CafeTagEntity cafeTag;

	private int taggingCount;
}
