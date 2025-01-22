package com.cafegory.cafegory.cafe.repository;

import com.cafegory.cafegory.cafe.domain.Review;
import com.cafegory.cafegory.member.domain.MemberId;

import java.util.List;

public interface ReviewRepository {

	List<Review> findAllByMemberId(MemberId memberId);
}
