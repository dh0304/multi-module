package com.cafegory.db.study;

import com.cafegory.db.cafe.cafe.CafeEntity;
import com.cafegory.db.config.JpaTest;
import com.cafegory.db.member.MemberEntity;
import com.cafegory.db.study.study.CafeStudyEntity;
import com.cafegory.db.study.studymember.CafeStudyMemberEntity;
import com.cafegory.db.study.studymember.StudyMemberJpaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static com.cafegory.db.testfixtures.persister.cafe.CafeContextPersister.aCafe;
import static com.cafegory.db.testfixtures.persister.member.MemberPersister.aMember;
import static com.cafegory.db.testfixtures.persister.study.StudyConextPersister.aStudy;
import static com.cafegory.db.testfixtures.persister.study.StudyMemberPersister.aStudyMember;
import static org.assertj.core.api.Assertions.assertThat;

class StudyMemberJpaRepositoryTest extends JpaTest {

	@Autowired
	private StudyMemberJpaRepository sut;

	@Test
	void findByCafeStudy_IdAndMember_Id() {
		// given
		CafeEntity cafe = aCafe().persistWith7daysFrom9To21();
		MemberEntity coordinator = aMember().asCoordinator().persist();

		CafeStudyEntity study = aStudy().withCafe(cafe).withMember(coordinator).persist();
		aStudyMember().asCoordinator().withMember(coordinator).withStudy(study).persist();
		// when
		Optional<CafeStudyMemberEntity> cafeStudyMembers = sut.findByCafeStudy_IdAndMember_Id(study.getId(),
			coordinator.getId());
		// then
		assertThat(cafeStudyMembers).isPresent();
	}
}
