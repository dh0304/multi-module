package com.cafegory.domain.member.repository;

import com.cafegory.domain.member.domain.Member;
import com.cafegory.domain.member.domain.MemberContent;
import com.cafegory.domain.member.domain.MemberId;

public interface MemberRepository {

	MemberId save(Member content);

	void update(MemberContent content, MemberId memberId);

	void updateRefreshToken(MemberId memberId, String refreshToken);
}
