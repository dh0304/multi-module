package com.cafegory.domain.study.implement;

import com.cafegory.domain.member.domain.MemberId;
import com.cafegory.domain.study.domain.Review;
import com.cafegory.domain.study.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ReviewReader {

	private final ReviewRepository reviewRepository;

	public List<Review> readBy(MemberId memberId) {
		return reviewRepository.findAllByMemberId(memberId);
	}
}
