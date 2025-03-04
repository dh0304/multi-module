package com.cafegory.db.cafe.menu;

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
@Table(name = "menu")
public class MenuEntity extends BaseEntity {

	@Id
	@GeneratedValue
	@Column(name = "menu_id")
	private Long id;

	private String name;
	private String price;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cafe_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
	private CafeEntity cafe;
}
