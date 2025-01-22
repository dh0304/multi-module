package com.cafegory.cafegory.member.repository;

import com.cafegory.cafegory.member.domain.Member;
import com.cafegory.cafegory.member.domain.MemberContent;
import com.cafegory.cafegory.member.domain.MemberId;

public interface MemberRepository {

	MemberId save(Member content);

	void update(MemberContent content, MemberId memberId);

	void updateRefreshToken(MemberId memberId, String refreshToken);
}
