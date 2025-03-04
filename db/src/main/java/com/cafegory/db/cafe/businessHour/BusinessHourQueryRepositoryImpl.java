package com.cafegory.db.cafe.businessHour;

import com.cafegory.domain.cafe.domain.BusinessHour;
import com.cafegory.domain.cafe.domain.CafeId;
import com.cafegory.domain.cafe.repository.BusinessHourQueryRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.DayOfWeek;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class BusinessHourQueryRepositoryImpl implements BusinessHourQueryRepository {

	private final JPAQueryFactory jpaQueryFactory;

	@Override
	public Optional<BusinessHour> findBy(CafeId cafeId, DayOfWeek dayOfWeek) {
		BusinessHourEntity businessHourEntity =
			jpaQueryFactory.select(QBusinessHourEntity.businessHourEntity)
				.from(QBusinessHourEntity.businessHourEntity)
				.where(QBusinessHourEntity.businessHourEntity.cafe.id.eq(cafeId.getId())
					.and(QBusinessHourEntity.businessHourEntity.dayOfWeek.eq(dayOfWeek)))
				.fetchOne();

		return Optional.ofNullable(businessHourEntity).map(BusinessHourEntity::toBusinessHour);
	}
}
