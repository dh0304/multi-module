package member.repository;

import member.domain.Member;
import member.domain.MemberContent;
import member.domain.MemberId;

public interface MemberRepository {

	MemberId save(Member content);

	void update(MemberContent content, MemberId memberId);

	void updateRefreshToken(MemberId memberId, String refreshToken);
}
