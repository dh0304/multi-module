package member.service;

import lombok.RequiredArgsConstructor;
import member.domain.Member;
import member.domain.MemberContent;
import member.domain.MemberId;
import member.implement.MemberReader;
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
