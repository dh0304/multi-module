package com.cafegory.auth.implement;

import com.cafegory.domain.common.exception.CafegoryException;
import com.cafegory.domain.member.domain.Member;
import com.cafegory.domain.member.domain.MemberContent;
import com.cafegory.domain.member.domain.MemberId;
import com.cafegory.domain.member.domain.Role;
import com.cafegory.domain.member.implement.MemberEditor;
import com.cafegory.domain.member.implement.MemberReader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import static com.cafegory.domain.common.exception.ExceptionType.MEMBER_ALREADY_EXISTS;

@Component
@RequiredArgsConstructor
public class SignupProcessor {

	private final MemberReader memberReader;
	private final MemberEditor memberEditor;

	@Transactional
	public MemberId signup(String email, String nickname) {
		if (memberReader.exists(email)) {
			throw new CafegoryException(MEMBER_ALREADY_EXISTS);
		}
		return memberEditor.save(createMember(email, nickname));
	}

	private Member createMember(String email, String nickname) {
		return Member.builder()
			.role(Role.USER)
			.email(email)
			.content(
				MemberContent.builder()
					.nickname(nickname)
					.build()
			)
			.build();
	}
}
