package com.cafegory.cafegory.member.repository;

import com.cafegory.cafegory.member.domain.Member;

import java.util.Optional;

public interface MemberQueryRepository {

	Optional<Member> findByEmail(String email);

	Optional<Member> findById(Long id);

	boolean existsByEmail(String email);
}
