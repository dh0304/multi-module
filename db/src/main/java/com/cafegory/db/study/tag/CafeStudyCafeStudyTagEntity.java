package com.cafegory.db.study.tag;

import com.cafegory.db.BaseEntity;
import com.cafegory.db.study.study.CafeStudyEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Where(clause = "deleted_date IS NULL")
@Table(name = "cafe_study_cafe_study_tag")
public class CafeStudyCafeStudyTagEntity extends BaseEntity {

	@Id
	@GeneratedValue
	@Column(name = "cafe_study_cafe_study_tag_id")
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cafe_study_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
	private CafeStudyEntity cafeStudy;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cafe_study_tag_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
	private CafeStudyTagEntity cafeStudyTag;

	public CafeStudyCafeStudyTagEntity(Long studyId, Long studyTagId) {
		this.cafeStudy = new CafeStudyEntity(studyId);
		this.cafeStudyTag = new CafeStudyTagEntity(studyTagId);
	}

	@Builder
	private CafeStudyCafeStudyTagEntity(CafeStudyEntity cafeStudy, CafeStudyTagEntity cafeStudyTag) {
		this.cafeStudy = cafeStudy;
		this.cafeStudyTag = cafeStudyTag;
	}
}
