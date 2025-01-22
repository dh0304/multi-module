package com.cafegory.cafegory.member.service;

import com.cafegory.cafegory.member.domain.Member;
import com.cafegory.cafegory.member.domain.MemberId;
import com.cafegory.cafegory.member.implement.MemberReader;
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
