package com.cafegory.db.study.tag;

import com.cafegory.db.BaseEntity;
import com.cafegory.domain.study.domain.CafeStudyTagType;
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
@Table(name = "cafe_study_tag")
public class CafeStudyTagEntity extends BaseEntity {

	@Id
	@GeneratedValue
	@Column(name = "cafe_study_tag_id")
	private Long id;

	@Enumerated(EnumType.STRING)
	private CafeStudyTagType type;

	public CafeStudyTagEntity(Long id) {
		this.id = id;
	}
}
