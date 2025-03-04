package com.cafegory.domain.study.domain;

import com.cafegory.domain.member.domain.MemberId;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Coordinator {

	private MemberId id;
	private String nickname;

	public boolean hasId(MemberId id) {
		return this.id.isSameId(id);
	}

	public boolean isSameCoordinator(Coordinator coordinator) {
		return this.id.isSameId(coordinator.getId());
	}
}
