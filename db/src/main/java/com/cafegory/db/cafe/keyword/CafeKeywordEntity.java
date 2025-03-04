package com.cafegory.db.cafe.keyword;

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
@Table(name = "cafe_keyword")
public class CafeKeywordEntity extends BaseEntity {

	@Id
	@GeneratedValue
	@Column(name = "cafe_keyword_id")
	private Long id;

	private String keyword;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cafe_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
	private CafeEntity cafe;
}
