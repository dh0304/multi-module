package com.cafegory.db.member;

import com.cafegory.domain.member.domain.Member;
import com.cafegory.domain.member.repository.MemberQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MemberQueryRepositoryImpl implements MemberQueryRepository {

	private final MemberJpaRepository memberJpaRepository;

	@Override
	public Optional<Member> findByEmail(String email) {
		return memberJpaRepository.findByEmail(email).map(MemberEntity::toMember);
	}

	@Override
	public Optional<Member> findById(Long id) {
		return memberJpaRepository.findById(id).map(MemberEntity::toMember);
	}

	@Override
	public boolean existsByEmail(String email) {
		return memberJpaRepository.existsByEmail(email);
	}
}
