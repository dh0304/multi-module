package com.cafegory.domain.member.service;

import com.cafegory.domain.member.domain.Member;
import com.cafegory.domain.member.domain.MemberId;
import com.cafegory.domain.member.implement.MemberReader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

	private final MemberReader memberReader;

	public Member getMember(MemberId memberId) {
		return memberReader.read(memberId);
	}
}
