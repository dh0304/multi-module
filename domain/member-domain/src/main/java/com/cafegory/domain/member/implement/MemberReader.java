package com.cafegory.domain.member.implement;

import com.cafegory.domain.common.exception.CafegoryException;
import com.cafegory.domain.common.exception.ExceptionType;
import com.cafegory.domain.member.domain.Member;
import com.cafegory.domain.member.domain.MemberId;
import lombok.RequiredArgsConstructor;
import com.cafegory.domain.member.repository.MemberQueryRepository;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class MemberReader {

	private final MemberQueryRepository memberQueryRepository;

	public boolean exists(String email) {
		return memberQueryRepository.existsByEmail(email);
	}

	public Member read(String email) {
		return memberQueryRepository.findByEmail(email)
			.orElseThrow(() -> new CafegoryException(ExceptionType.MEMBER_NOT_FOUND));
	}

	public Member read(MemberId memberId) {
		return memberQueryRepository.findById(memberId.getId())
			.orElseThrow(() -> new CafegoryException(ExceptionType.MEMBER_NOT_FOUND));
	}
}
