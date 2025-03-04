package com.cafegory.domain.cafe.repository;

import com.cafegory.domain.cafe.domain.Cafe;
import com.cafegory.domain.cafe.domain.CafeId;

import java.util.Optional;

public interface CafeRepository {

	Optional<Cafe> findById(CafeId cafeId);

	Optional<Cafe> findWithTags(CafeId cafeId);
}
