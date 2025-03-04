package com.cafegory.db.cafe.businessHour;

import com.cafegory.db.BaseEntity;
import com.cafegory.db.cafe.cafe.CafeEntity;
import com.cafegory.domain.cafe.domain.BusinessHour;
import com.cafegory.domain.cafe.domain.BusinessHourId;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Where;

import java.time.DayOfWeek;
import java.time.LocalTime;

@Builder
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Setter
@Where(clause = "deleted_date IS NULL")
@Table(name = "business_hour")
public class BusinessHourEntity extends BaseEntity {

	@Id
	@GeneratedValue
	@Column(name = "business_hour_id")
	private Long id;

	@Column(name = "day_of_week")
	private DayOfWeek dayOfWeek;

	/// columnDefinition 옵션은 나중에 DB에 직접 적용하고 삭제할 것
	@Column(columnDefinition = "TIME(6)")
	private LocalTime openingTime;
	@Column(columnDefinition = "TIME(6)")
	private LocalTime closingTime;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cafe_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
	private CafeEntity cafe;

	public BusinessHour toBusinessHour() {
		return BusinessHour.builder()
			.id(new BusinessHourId(this.id))
			.dayOfWeek(this.dayOfWeek)
			.openingTme(this.openingTime)
			.closingTme(this.closingTime)
			.build();
	}
}
