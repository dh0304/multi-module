package com.cafegory.api.security;

import com.cafegory.auth.exception.JwtTokenAuthenticationException;
import com.cafegory.domain.member.repository.MemberQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.cafegory.domain.common.exception.ExceptionType.JWT_SUBJECT_NOT_FOUND;

@Component
@RequiredArgsConstructor
public class MemberDetailsService {

	private final MemberQueryRepository memberQueryRepository;

	public CustomUserDetails loadUserByUserId(final String claimSubjectValue) throws UsernameNotFoundException {
		Long memberId = Long.parseLong(claimSubjectValue);

		return memberQueryRepository.findById(memberId)
			.map(member -> new CustomUserDetails(member.getId().getId(), List.of(member.getRole())))
			.orElseThrow(() -> new JwtTokenAuthenticationException(JWT_SUBJECT_NOT_FOUND));
	}
}
