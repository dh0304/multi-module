package com.cafegory.db.study.review;

import com.cafegory.domain.member.domain.MemberId;
import com.cafegory.domain.study.domain.Review;
import com.cafegory.domain.study.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class ReviewRepositoryImpl implements ReviewRepository {

	private final ReviewJpaRepository reviewJpaRepository;

	@Override
	public List<Review> findAllByMemberId(MemberId memberId) {
		List<ReviewEntity> reviewEntities = reviewJpaRepository.findAllByMemberId(memberId.getId());

		return reviewEntities.stream()
			.map(ReviewEntity::toReview)
			.collect(Collectors.toList());
	}
}
