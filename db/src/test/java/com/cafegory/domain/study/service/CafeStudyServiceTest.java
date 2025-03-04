package com.cafegory.domain.study.service;

import com.cafegory.db.cafe.cafe.CafeEntity;
import com.cafegory.db.member.MemberEntity;
import com.cafegory.domain.cafe.domain.CafeId;
import com.cafegory.domain.common.exception.CafegoryException;
import com.cafegory.domain.common.time.TimeUtil;
import com.cafegory.domain.config.ServiceTest;
import com.cafegory.domain.member.domain.MemberId;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.stream.Stream;

import static com.cafegory.db.testfixtures.persister.cafe.CafeContextPersister.aCafe;
import static com.cafegory.db.testfixtures.persister.member.MemberPersister.aMember;
import static com.cafegory.domain.common.exception.ExceptionType.*;
import static com.cafegory.domain.study.testfixtures.builder.ScheduleBuilder.aSchedule;
import static com.cafegory.domain.study.testfixtures.builder.StudyContentBuilder.aStudyContent;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CafeStudyServiceTest extends ServiceTest {

	@Autowired
	private CafeStudyService sut;
	@Autowired
	private TimeUtil timeUtil;

	@Test
	@DisplayName("카공 시작은 현재 시간으로부터 1시간 이전일 수 없다.")
	void study_starts_1hours_before_now() {
		//given
		CafeEntity cafe = aCafe().persistWith7daysFrom9To21();
		MemberEntity coordinator = aMember().asCoordinator().persist();

		LocalDateTime start = timeUtil.now().plusHours(1).minusMinutes(1);
		LocalDateTime end = start.plusHours(1);
		//then
		assertThatThrownBy(
			() -> sut.createStudy(
				new MemberId(coordinator.getId()),
				timeUtil.now(),
				aStudyContent().with(aSchedule().withStartDateTime(start).withEndDateTime(end)).build(),
				new CafeId(cafe.getId())))
			.isInstanceOf(CafegoryException.class)
			.hasMessage(STUDY_ONCE_WRONG_START_TIME.getErrorMessage());
	}

	@Test
	@DisplayName("카공 시작은 현재 날짜로부터 한달 이내여야 한다.")
	void study_start_date_after_one_month() {
		//given
		CafeEntity cafe = aCafe().persistWith24For7();
		MemberEntity coordinator = aMember().asCoordinator().persist();

		LocalDateTime start = timeUtil.now().plusMonths(1).plusDays(1);
		LocalDateTime end = start.plusHours(1);
		//then
		assertThatThrownBy(
			() -> sut.createStudy(
				new MemberId(coordinator.getId()),
				timeUtil.now(),
				aStudyContent().with(aSchedule().withStartDateTime(start).withEndDateTime(end)).build(),
				new CafeId(cafe.getId())))
			.isInstanceOf(CafegoryException.class)
			.hasMessage(CAFE_STUDY_WRONG_START_DATE.getErrorMessage());
	}

	@ParameterizedTest()
	@MethodSource("provideStartAndEndDateTime1")
	@DisplayName("카페 영업시간 밖의 시간에 카공을 만들 수 없다.")
	void study_can_not_start_outside_cafe_business_hours(LocalDateTime start, LocalDateTime end) {
		//given
		LocalDateTime now = timeUtil.localDateTime(2000, 1, 1, 0, 0, 0);

		CafeEntity cafe = aCafe().persistWith7daysFrom9To21();
		MemberEntity coordinator = aMember().asCoordinator().persist();
		//then
		assertThatThrownBy(() -> sut.createStudy(
			new MemberId(coordinator.getId()),
			timeUtil.localDateTime(2000, 1, 1, 0, 0, 0),
			aStudyContent().with(aSchedule().withStartDateTime(start).withEndDateTime(end)).build(),
			new CafeId(cafe.getId())))
			.isInstanceOf(CafegoryException.class)
			.hasMessage(STUDY_ONCE_CREATE_BETWEEN_CAFE_BUSINESS_HOURS.getErrorMessage());
	}

	static Stream<Arguments> provideStartAndEndDateTime1() {
		return Stream.of(Arguments.of(LocalDateTime.of(2000, 1, 1, 8, 59, 59), LocalDateTime.of(2000, 1, 1, 10, 0)),
			Arguments.of(LocalDateTime.of(2000, 1, 1, 8, 0), LocalDateTime.of(2000, 1, 1, 9, 0, 0)),
			Arguments.of(LocalDateTime.of(2000, 1, 1, 20, 0), LocalDateTime.of(2000, 1, 1, 21, 0, 1)),
			Arguments.of(LocalDateTime.of(2000, 1, 1, 20, 59, 59), LocalDateTime.of(2000, 1, 1, 22, 0)));
	}
}
