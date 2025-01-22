package cafe.implement;

import cafe.domain.Review;
import cafe.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import member.domain.MemberId;
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
