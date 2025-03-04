package com.cafegory.db.cafe.cafe;

import com.cafegory.domain.cafe.domain.Cafe;
import com.cafegory.domain.cafe.domain.CafeId;
import com.cafegory.domain.cafe.repository.CafeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CafeRepositoryImpl implements CafeRepository {

	private final CafeJpaRepository cafeJpaRepository;

	@Override
	public Optional<Cafe> findById(CafeId cafeId) {
		return cafeJpaRepository.findById(cafeId.getId()).map(CafeEntity::toCafe);
	}

	@Override
	public Optional<Cafe> findWithTags(CafeId cafeId) {
		return cafeJpaRepository.findWithTags(cafeId.getId()).map(CafeEntity::toCafe);
	}
}
