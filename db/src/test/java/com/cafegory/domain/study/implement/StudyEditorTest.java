package com.cafegory.domain.study.implement;

import com.cafegory.db.cafe.cafe.CafeEntity;
import com.cafegory.db.member.MemberEntity;
import com.cafegory.db.study.study.CafeStudyEntity;
import com.cafegory.db.testfixtures.persister.study.StudyConextPersister;
import com.cafegory.domain.common.time.TimeUtil;
import com.cafegory.domain.config.ServiceTest;
import com.cafegory.domain.member.domain.MemberId;
import com.cafegory.domain.study.domain.StudyId;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static com.cafegory.db.testfixtures.persister.cafe.CafeContextPersister.aCafe;
import static com.cafegory.db.testfixtures.persister.member.MemberPersister.aMember;
import static com.cafegory.db.testfixtures.persister.study.StudyMemberPersister.aStudyMember;
import static com.cafegory.domain.study.domain.StudyRole.COORDINATOR;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class StudyEditorTest extends ServiceTest {

	@Autowired
	private StudyEditor sut;

	@Autowired
	private TimeUtil timeUtil;

	@Test
	@DisplayName("카공을 삭제할 때 관련된 데이터를 같이 삭제한다.")
	void remove_study_with_cascade() {
		//given
		CafeEntity cafe = aCafe().persistWith7daysFrom9To21();
		MemberEntity coordinator = aMember().asCoordinator().persist();

		CafeStudyEntity study = StudyConextPersister.aStudy().withCafe(cafe).withMember(coordinator).persist();
		aStudyMember().withStudy(study).withMember(coordinator).withStudyRole(COORDINATOR).persist();
		//when & then
		assertDoesNotThrow(
			() -> sut.removeWithCascade(new StudyId(study.getId()), new MemberId(coordinator.getId()), timeUtil.now())
		);
	}
}