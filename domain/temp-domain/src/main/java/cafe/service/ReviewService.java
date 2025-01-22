package cafe.service;

import cafe.domain.Review;
import cafe.implement.ReviewReader;
import lombok.RequiredArgsConstructor;
import member.domain.MemberId;
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
