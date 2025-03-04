//package com.cafegory.auth.implement;
//
//import com.cafegory.db.member.MemberEntity;
//import com.cafegory.db.member.MemberJpaRepository;
//import com.cafegory.domain.common.exception.CafegoryException;
//import com.cafegory.domain.common.exception.ExceptionType;
//import com.cafegory.domain.config.ServiceTest;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import java.util.List;
//
//import static com.cafegory.db.testfixtures.persister.member.MemberPersister.aMember;
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.assertj.core.api.Assertions.assertThatThrownBy;
//
//class SignupProcessorTest extends ServiceTest {
//
//	@Autowired
//	private SignupProcessor sut;
//	@Autowired
//	private MemberJpaRepository memberJpaRepository;
//
//	@Test
//	@DisplayName("회원가입을 한다.")
//	void signup() {
//		//when
//		sut.signup("new@gmail.com", "newUser");
//		//then
//		List<MemberEntity> members = memberJpaRepository.findAll();
//		assertThat(members.size()).isEqualTo(1);
//	}
//
//	@Test
//	@DisplayName("이미 등록된 이메일로는 회원가입을 할 수 없다.")
//	void email_already_registered_prevents_signup() {
//		//given
//		MemberPersister.aMember().withEmail("new@gmail.com").persist();
//		//then
//		assertThatThrownBy(() -> sut.signup("new@gmail.com", "newUser"))
//			.isInstanceOf(CafegoryException.class)
//			.hasMessage(ExceptionType.MEMBER_ALREADY_EXISTS.getErrorMessage());
//	}
//}
