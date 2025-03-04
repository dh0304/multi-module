package com.cafegory.db.study.review;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReviewJpaRepository extends JpaRepository<ReviewEntity, Long> {

	@Query("select distinct r from ReviewEntity r" +
		" inner join fetch r.cafe" +
		" left join fetch r.reviewCafeTag")
	List<ReviewEntity> findAllByMemberId(Long memberId);
}
