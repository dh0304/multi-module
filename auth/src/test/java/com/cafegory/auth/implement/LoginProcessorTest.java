//package com.cafegory.auth.implement;
//
//import com.cafegory.auth.domain.JwtToken;
//import com.cafegory.db.member.MemberEntity;
//import com.cafegory.domain.common.exception.CafegoryException;
//import com.cafegory.domain.common.exception.ExceptionType;
//import com.cafegory.domain.config.ServiceTest;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Import;
//
//import static com.cafegory.db.testfixtures.persister.member.MemberPersister.aMember;
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.assertj.core.api.Assertions.assertThatThrownBy;
//
//@Import(LoginProcessor.class)
//class LoginProcessorTest extends ServiceTest {
//
//	@Autowired
//	private LoginProcessor sut;
//
//	@Test
//	@DisplayName("로그인이 성공하면 토큰을 발급한다.")
//	void login_succeed() {
//		//given
//		MemberEntity member = MemberPersister.aMember().persist();
//		//when
//		JwtToken token = sut.login(member.getEmail());
//		//then
//		assertThat(token).isNotNull();
//	}
//
//	@Test
//	@DisplayName("로그인이 실패한다.")
//	void login_fail() {
//		assertThatThrownBy(() -> sut.login("empty@gmail.com"))
//			.isInstanceOf(CafegoryException.class)
//			.hasMessage(ExceptionType.MEMBER_NOT_FOUND.getErrorMessage());
//	}
//}
