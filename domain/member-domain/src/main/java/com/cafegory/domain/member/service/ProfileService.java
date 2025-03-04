package com.cafegory.domain.member.service;

import com.cafegory.domain.member.domain.Member;
import com.cafegory.domain.member.domain.MemberContent;
import com.cafegory.domain.member.domain.MemberId;
import com.cafegory.domain.member.implement.MemberReader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfileService {

	private final MemberReader memberReader;

	public MemberContent getWelcomeProfile(MemberId memberId) {
		Member member = memberReader.read(memberId);
		return member.getContent();
	}
}
