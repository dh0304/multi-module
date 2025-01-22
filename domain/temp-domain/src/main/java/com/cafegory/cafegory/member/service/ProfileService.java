package com.cafegory.cafegory.member.service;

import com.cafegory.cafegory.member.domain.Member;
import com.cafegory.cafegory.member.domain.MemberContent;
import com.cafegory.cafegory.member.domain.MemberId;
import com.cafegory.cafegory.member.implement.MemberReader;
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
