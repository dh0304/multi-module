package com.cafegory.domain.study.service;

import com.cafegory.domain.member.domain.MemberId;
import com.cafegory.domain.study.domain.Review;
import com.cafegory.domain.study.implement.ReviewReader;
import lombok.RequiredArgsConstructor;
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
