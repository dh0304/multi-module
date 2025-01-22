package com.cafegory.cafegory.cafe.repository;

import com.cafegory.cafegory.cafe.domain.Cafe;
import com.cafegory.cafegory.cafe.domain.CafeId;

import java.util.Optional;

public interface CafeRepository {

	Optional<Cafe> findById(CafeId cafeId);

	Optional<Cafe> findWithTags(CafeId cafeId);
}
