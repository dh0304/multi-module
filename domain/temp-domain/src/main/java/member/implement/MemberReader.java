package member.implement;

import exception.CafegoryException;
import exception.ExceptionType;
import lombok.RequiredArgsConstructor;
import member.domain.Member;
import member.domain.MemberId;
import member.repository.MemberQueryRepository;
import org.springframework.stereotype.Component;

import static exception.ExceptionType.*;

@Component
@RequiredArgsConstructor
public class MemberReader {

	private final MemberQueryRepository memberQueryRepository;

	public boolean exists(String email) {
		return memberQueryRepository.existsByEmail(email);
	}

	public Member read(String email) {
		return memberQueryRepository.findByEmail(email)
			.orElseThrow(() -> new CafegoryException(MEMBER_NOT_FOUND));
	}

	public Member read(MemberId memberId) {
		return memberQueryRepository.findById(memberId.getId())
			.orElseThrow(() -> new CafegoryException(MEMBER_NOT_FOUND));
	}
}
