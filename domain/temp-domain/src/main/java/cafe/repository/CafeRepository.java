package cafe.repository;

import cafe.domain.Cafe;
import cafe.domain.CafeId;

import java.util.Optional;

public interface CafeRepository {

	Optional<Cafe> findById(CafeId cafeId);

	Optional<Cafe> findWithTags(CafeId cafeId);
}
