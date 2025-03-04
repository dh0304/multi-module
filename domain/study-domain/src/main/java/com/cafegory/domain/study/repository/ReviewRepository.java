package com.cafegory.domain.study.repository;


import com.cafegory.domain.member.domain.MemberId;
import com.cafegory.domain.study.domain.Review;

import java.util.List;

public interface ReviewRepository {

	List<Review> findAllByMemberId(MemberId memberId);
}
