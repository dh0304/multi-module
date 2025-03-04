package com.cafegory.db.cafe.cafe;

import com.cafegory.domain.cafe.domain.CafeTagType;
import com.cafegory.domain.cafe.dto.CafeSearchListRequest;
import com.cafegory.domain.common.SliceResponse;
import com.cafegory.domain.common.exception.CafegoryException;
import com.cafegory.domain.common.exception.ExceptionType;
import com.cafegory.db.util.PagingUtil;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

import static com.cafegory.db.cafe.businessHour.QBusinessHourEntity.businessHourEntity;
import static com.cafegory.db.cafe.cafe.QCafeEntity.cafeEntity;

@Repository
@RequiredArgsConstructor
public class CafeQueryDslRepository {

	private final JPAQueryFactory queryFactory;

	public SliceResponse<CafeEntity> findCafeByRegionAndKeyword(CafeSearchListRequest request) {
		Pageable pageable = PagingUtil.of(request.getPage(), request.getSizePerPage());

		JPAQuery<Long> cafeIds = queryFactory
			.select(businessHourEntity.cafe.id).distinct()
			.from(businessHourEntity)
			.join(businessHourEntity.cafe, cafeEntity)
			.where(
				businessHourContains(request.getOpeningDateTime(), request.getClosingDateTime()),
				keywordContains(request.getKeyword())
					.or(cafeNameContains(request.getKeyword())),
				hasAllCafeTagTypes(request.getCafeTags())
			)
			.orderBy(cafeEntity.cafeStudies.size().asc());

		JPAQuery<CafeEntity> query = queryFactory
			.select(cafeEntity)
			.from(cafeEntity)
			.where(cafeEntity.id.in(cafeIds));

		Slice<CafeEntity> slicedCafeEntities = PagingUtil.toSlice(query, pageable);
		return SliceResponse.of(slicedCafeEntities.hasNext(), slicedCafeEntities.getNumber(), slicedCafeEntities.getContent());
	}

	private BooleanExpression keywordContains(String keyword) {
		// 만약 이 메서드가 동작하지 않는다면 DB의 맞는 Expressions.stringTemplate 의 내부 구문을 바꿔야 한다.
		// DB에 등록된 키워드와 파라미터의 키워드 둘다 공백제거 한뒤 비교한다.
		return keyword == null ? null :
			Expressions.stringTemplate("function('replace', {0}, ' ', '')", cafeEntity.cafeKeywords.any().keyword)
				.likeIgnoreCase("%" + keyword.replace(" ", "") + "%");
	}

	private BooleanExpression cafeNameContains(String cafeName) {
		// 만약 이 메서드가 동작하지 않는다면 DB의 맞는 Expressions.stringTemplate 의 내부 구문을 바꿔야 한다.
		// DB에 등록된 키워드와 파라미터의 키워드 둘다 공백제거 한뒤 비교한다.
		return cafeName == null ? null :
			Expressions.stringTemplate("function('replace', {0}, ' ', '')", cafeEntity.name)
				.likeIgnoreCase("%" + cafeName.replace(" ", "") + "%");
	}

	private BooleanExpression businessHourContains(LocalDateTime openingDateTime, LocalDateTime closingDateTime) {
		boolean isStartDateAndEndDateEquals = openingDateTime.getDayOfWeek().equals(closingDateTime.getDayOfWeek());

		// 시간 유효성 검사
		if (openingDateTime.isAfter(closingDateTime) || openingDateTime.isEqual(closingDateTime)) {
			throw new CafegoryException(ExceptionType.CAFE_SEARCH_INVALID_TIME_RANGE);
		}

		// 시작시간과 종료시간이 같은 날인 경우
		if (isStartDateAndEndDateEquals) {
			return businessHourEntity.dayOfWeek.eq(openingDateTime.getDayOfWeek())
				.and(businessHourEntity.openingTime.loe(closingDateTime.toLocalTime())
					.and(businessHourEntity.closingTime.goe(openingDateTime.toLocalTime())));
		}

		// 시작시간과 종료시간이 다른 날인 경우
		return businessHourEntity.dayOfWeek.eq(closingDateTime.getDayOfWeek())
			.and(businessHourEntity.openingTime.loe(closingDateTime.toLocalTime()))
			.or((businessHourEntity.dayOfWeek.eq(openingDateTime.getDayOfWeek()))
				.and(businessHourEntity.closingTime.goe(openingDateTime.toLocalTime())));
	}

	private BooleanExpression hasAllCafeTagTypes(List<CafeTagType> cafeTagTypes) {
		if (cafeTagTypes == null || cafeTagTypes.isEmpty())
			return null;

		return cafeTagTypes.stream()
			.map(type -> cafeEntity.cafeCafeTags.any().cafeTag.type.eq(type))
			.reduce(BooleanExpression::and)
			.orElse(null);
	}
}
