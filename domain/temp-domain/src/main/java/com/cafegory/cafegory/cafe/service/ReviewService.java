package com.cafegory.cafegory.cafe.service;

import com.cafegory.cafegory.cafe.domain.Review;
import com.cafegory.cafegory.cafe.implement.ReviewReader;
import lombok.RequiredArgsConstructor;
import com.cafegory.cafegory.member.domain.MemberId;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

	private final ReviewReader reviewReader;

	public List<Review> getReviews(MemberId memberId) {
		return reviewReader.readBy(memberId);
	}
}
