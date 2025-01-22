package member.service;

import lombok.RequiredArgsConstructor;
import member.domain.Member;
import member.domain.MemberId;
import member.implement.MemberReader;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

	private final MemberReader memberReader;

	public Member getMember(MemberId memberId) {
		return memberReader.read(memberId);
	}
}
