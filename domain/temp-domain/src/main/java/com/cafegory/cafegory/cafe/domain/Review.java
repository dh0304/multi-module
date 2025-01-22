package com.cafegory.cafegory.cafe.domain;

import com.cafegory.cafegory.common.DateAudit;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class Review {

	private ReviewId id;
	private List<CafeTagType> tags;
	private Cafe cafe;

	private DateAudit dateAudit;

	public boolean hasId(ReviewId id) {
		return this.id.isSameId(id);
	}
}
