package com.cafegory.domain.member.repository;

import com.cafegory.domain.member.domain.Member;

import java.util.Optional;

public interface MemberQueryRepository {

	Optional<Member> findByEmail(String email);

	Optional<Member> findById(Long id);

	boolean existsByEmail(String email);
}
