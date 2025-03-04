package com.cafegory.domain.study.implement;

import com.cafegory.db.cafe.cafe.CafeEntity;
import com.cafegory.db.member.MemberEntity;
import com.cafegory.db.study.study.CafeStudyEntity;
import com.cafegory.domain.config.ServiceTest;
import com.cafegory.domain.study.domain.StudyId;
import com.cafegory.domain.study.domain.StudyMemberId;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static com.cafegory.db.testfixtures.persister.cafe.CafeContextPersister.aCafe;
import static com.cafegory.db.testfixtures.persister.member.MemberPersister.aMember;
import static com.cafegory.db.testfixtures.persister.study.StudyConextPersister.aStudy;
import static com.cafegory.db.testfixtures.persister.study.StudyMemberPersister.aStudyMember;
import static org.assertj.core.api.Assertions.assertThat;

class StudyMemberReaderTest extends ServiceTest {

	@Autowired
	private StudyMemberReader sut;

	@Test
	@DisplayName("카공에 참여한 현재 인원수를 찾는다.")
	void find_current_participants() {
		//given
		CafeEntity cafe = aCafe().persistWith7daysFrom9To21();

		MemberEntity coordinator = aMember().asCoordinator().persist();
		MemberEntity participant = aMember().asParticipant().persist();

		CafeStudyEntity study = aStudy().withCafe(cafe).withMember(coordinator).persist();
		aStudyMember().withStudy(study).withMember(coordinator).asCoordinator().persist();
		aStudyMember().withStudy(study).withMember(participant).asParticipant().persist();
		//when
		int result = sut.loadParticipantCount(new StudyId(study.getId()));
		//then
		assertThat(result).isEqualTo(2);
	}

	@Test
	@DisplayName("카공에 참여한 사람들의 아이디를 찾는다.")
	void find_current_participantIds() {
		//given
		CafeEntity cafe = aCafe().persistWith7daysFrom9To21();

		MemberEntity coordinator = aMember().asCoordinator().persist();
		MemberEntity participant = aMember().asParticipant().persist();

		CafeStudyEntity study = aStudy().withCafe(cafe).withMember(coordinator).persist();
		aStudyMember().withStudy(study).withMember(coordinator).asCoordinator().persist();
		aStudyMember().withStudy(study).withMember(participant).asParticipant().persist();
		//when
		List<StudyMemberId> result = sut.readParticipantIdsBy(new StudyId(study.getId()));
		//then
		assertThat(result).hasSize(2);
	}
}
