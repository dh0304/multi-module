package com.cafegory.cafegory.member.implement;

import com.cafegory.cafegory.member.domain.Member;
import com.cafegory.cafegory.member.domain.MemberContent;
import com.cafegory.cafegory.member.domain.MemberId;
import lombok.RequiredArgsConstructor;
import com.cafegory.cafegory.member.repository.MemberRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MemberEditor {

	private final MemberRepository memberRepository;

	public MemberId save(Member member) {
		return memberRepository.save(member);
	}

	public void edit(MemberContent content, MemberId memberId) {
		memberRepository.update(content, memberId);
	}

	public void updateRefreshToken(MemberId memberId, String refreshToken) {
		memberRepository.updateRefreshToken(memberId, refreshToken);
	}
}
