package cafe.repository;

import cafe.domain.Review;
import member.domain.MemberId;

import java.util.List;

public interface ReviewRepository {

	List<Review> findAllByMemberId(MemberId memberId);
}
