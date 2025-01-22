package member.implement;

import lombok.RequiredArgsConstructor;
import member.domain.Member;
import member.domain.MemberContent;
import member.domain.MemberId;
import member.repository.MemberRepository;
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
