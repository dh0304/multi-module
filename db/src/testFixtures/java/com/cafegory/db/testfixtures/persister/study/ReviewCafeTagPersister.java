package com.cafegory.db.testfixtures.persister.study;

import com.cafegory.db.cafe.tag.CafeTagEntity;
import com.cafegory.db.study.review.ReviewCafeTagEntity;
import com.cafegory.db.study.review.ReviewCafeTagJpaRepository;
import com.cafegory.db.study.review.ReviewEntity;

public class ReviewCafeTagPersister {

	private ReviewEntity review;
	private CafeTagEntity cafeTag;

	private ReviewCafeTagPersister() {
	}

	private ReviewCafeTagPersister(ReviewCafeTagPersister copy) {
		this.review = copy.review;
		this.cafeTag = copy.cafeTag;
	}

	public ReviewCafeTagPersister but() {
		return new ReviewCafeTagPersister(this);
	}

	public static ReviewCafeTagPersister aReviewCafeTag() {
		return new ReviewCafeTagPersister();
	}

	public ReviewCafeTagPersister withReview(ReviewEntity review) {
		this.review = review;
		return this;
	}

	public ReviewCafeTagPersister withCafeTag(CafeTagEntity cafeTag) {
		this.cafeTag = cafeTag;
		return this;
	}

	public ReviewCafeTagEntity build() {
		return ReviewCafeTagEntity.builder()
			.review(this.review)
			.cafeTag(this.cafeTag)
			.build();
	}

	public static class ReviewCafeTagRepoHolder {
		private static ReviewCafeTagJpaRepository reviewCafeTagJpaRepository;

		public static void init(ReviewCafeTagJpaRepository reviewCafeTagRepo) {
			reviewCafeTagJpaRepository = reviewCafeTagRepo;
		}
	}

	public ReviewCafeTagEntity persist() {
		return ReviewCafeTagRepoHolder.reviewCafeTagJpaRepository.save(build());
	}
}
