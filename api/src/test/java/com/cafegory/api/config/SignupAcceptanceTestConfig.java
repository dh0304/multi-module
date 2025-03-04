package com.cafegory.api.config;

import com.cafegory.api.helper.MemberSignupAcceptanceTestHelper;
import com.cafegory.auth.implement.LoginProcessor;
import com.cafegory.auth.implement.SignupProcessor;
import com.cafegory.auth.service.LoginService;
import com.cafegory.auth.testfixtures.spy.SpyLoginService;
import com.cafegory.domain.member.implement.MemberEditor;
import com.cafegory.domain.member.implement.MemberReader;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

@TestConfiguration
public class SignupAcceptanceTestConfig {

	@Bean
	@Primary
	public LoginService spyLoginService(MemberReader memberReader, LoginProcessor loginProcessor,
										SignupProcessor signupProcessor, MemberEditor memberEditor) {
		return new SpyLoginService(memberReader, loginProcessor, signupProcessor, memberEditor);
	}

	@Bean
	public MemberSignupAcceptanceTestHelper memberSignupAcceptanceTestHelper() {
		return new MemberSignupAcceptanceTestHelper();
	}
}
