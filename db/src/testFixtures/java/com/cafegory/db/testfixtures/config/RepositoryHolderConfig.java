package com.cafegory.db.testfixtures.config;

import com.cafegory.db.cafe.businessHour.BusinessHourJpaRepository;
import com.cafegory.db.cafe.cafe.CafeJpaRepository;
import com.cafegory.db.cafe.keyword.CafeKeywordJpaRepository;
import com.cafegory.db.cafe.menu.MenuJpaRepository;
import com.cafegory.db.cafe.tag.CafeCafeTagJpaRepository;
import com.cafegory.db.cafe.tag.CafeTagJpaRepository;
import com.cafegory.db.member.MemberJpaRepository;
import com.cafegory.db.qna.CommentJpaRepository;
import com.cafegory.db.study.review.ReviewCafeTagJpaRepository;
import com.cafegory.db.study.review.ReviewJpaRepository;
import com.cafegory.db.study.study.StudyJpaRepository;
import com.cafegory.db.study.studymember.StudyMemberJpaRepository;
import com.cafegory.db.study.tag.StudyStudyTagJpaRepository;
import com.cafegory.db.study.tag.StudyTagJpaRepository;
import com.cafegory.db.testfixtures.persister.cafe.*;
import com.cafegory.db.testfixtures.persister.member.MemberPersister;
import com.cafegory.db.testfixtures.persister.qna.CommentPersister;
import com.cafegory.db.testfixtures.persister.study.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestComponent;

@TestComponent
public class RepositoryHolderConfig {

	@Autowired
	private CafeJpaRepository cafeJpaRepository;
	@Autowired
	private MemberJpaRepository memberJpaRepository;
	@Autowired
	private StudyJpaRepository studyRepository;
	@Autowired
	private BusinessHourJpaRepository businessHourRepository;
	@Autowired
	private CommentJpaRepository commentRepository;
	@Autowired
	private CafeKeywordJpaRepository cafeKeywordJpaRepository;
	@Autowired
	private StudyTagJpaRepository studyTagRepository;
	@Autowired
	private StudyStudyTagJpaRepository studyStudyTagRepository;
	@Autowired
	private CafeTagJpaRepository cafeTagJpaRepository;
	@Autowired
	private CafeCafeTagJpaRepository cafeCafeTagJpaRepository;
	@Autowired
	private StudyMemberJpaRepository studyMemberJpaRepository;
	@Autowired
	private MenuJpaRepository menuJpaRepository;
	@Autowired
	private ReviewJpaRepository reviewJpaRepository;
	@Autowired
	private ReviewCafeTagJpaRepository reviewCafeTagJpaRepository;

	public void init() {
		CafeContextPersister.CafeRepoHolder.init(cafeJpaRepository);
		BusinessHourPersister.BusinessHourRepoHolder.init(businessHourRepository);
		CafeKeywordPersister.CafeKeywordRepoHolder.init(cafeKeywordJpaRepository);
		CafeTagPersister.CafeTagRepoHolder.init(cafeTagJpaRepository);
		CafeCafeTagPersister.CafeCafeTagRepoHolder.init(cafeCafeTagJpaRepository);
		MenuPersister.MenuRepoHolder.init(menuJpaRepository);
		ReviewContextPersister.ReviewRepoHolder.init(reviewJpaRepository);
		ReviewCafeTagPersister.ReviewCafeTagRepoHolder.init(reviewCafeTagJpaRepository);

		MemberPersister.MemberRepoHolder.init(memberJpaRepository);

		StudyConextPersister.StudyRepoHolder.init(studyRepository);
		StudyTagPersister.StudyTagRepoHolder.init(studyTagRepository);
		StudyStudyTagBuilder.StudyStudyTagRepoHolder.init(studyStudyTagRepository);
		StudyMemberPersister.StudyMemberRepoHolder.init(studyMemberJpaRepository);

		CommentPersister.CommentRepoHolder.init(commentRepository);
	}
}