package com.cafegory.domain.study.domain;

import com.cafegory.domain.cafe.domain.Cafe;
import com.cafegory.domain.cafe.domain.CafeTagType;
import com.cafegory.domain.common.DateAudit;
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
