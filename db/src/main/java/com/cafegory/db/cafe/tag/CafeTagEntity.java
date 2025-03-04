package com.cafegory.db.cafe.tag;

import com.cafegory.db.BaseEntity;
import com.cafegory.domain.cafe.domain.CafeTagType;
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
@Table(name = "cafe_tag")
public class CafeTagEntity extends BaseEntity {

	@Id
	@GeneratedValue
	@Column(name = "cafe_tag_id")
	private Long id;

	@Enumerated(EnumType.STRING)
	private CafeTagType type;
}
