package com.cafegory.cafegory.member.implement;

import com.cafegory.cafegory.exception.CafegoryException;
import com.cafegory.cafegory.member.domain.Member;
import com.cafegory.cafegory.member.domain.MemberId;
import lombok.RequiredArgsConstructor;
import com.cafegory.cafegory.member.repository.MemberQueryRepository;
import org.springframework.stereotype.Component;

import static com.cafegory.cafegory.exception.ExceptionType.*;

@Component
@RequiredArgsConstructor
public class MemberReader {

	private final MemberQueryRepository memberQueryRepository;

	public boolean exists(String email) {
		return memberQueryRepository.existsByEmail(email);
	}

	public Member read(String email) {
		return memberQueryRepository.findByEmail(email)
			.orElseThrow(() -> new CafegoryException(MEMBER_NOT_FOUND));
	}

	public Member read(MemberId memberId) {
		return memberQueryRepository.findById(memberId.getId())
			.orElseThrow(() -> new CafegoryException(MEMBER_NOT_FOUND));
	}
}
