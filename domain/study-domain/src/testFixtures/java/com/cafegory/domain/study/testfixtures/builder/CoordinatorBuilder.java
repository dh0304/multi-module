package com.cafegory.domain.study.testfixtures.builder;


import com.cafegory.domain.member.domain.MemberId;
import com.cafegory.domain.study.domain.Coordinator;

public class CoordinatorBuilder {

	private MemberId id = new MemberId(1L);
	private String nickname = "테스트 닉네임";

	private CoordinatorBuilder() {
	}

	private CoordinatorBuilder(CoordinatorBuilder copy) {
		this.id = copy.id;
		this.nickname = copy.nickname;
	}

	public CoordinatorBuilder but() {
		return new CoordinatorBuilder(this);
	}

	public static CoordinatorBuilder aCoordinator() {
		return new CoordinatorBuilder();
	}

	public CoordinatorBuilder withId(Long id) {
		this.id = new MemberId(id);
		return this;
	}

	public CoordinatorBuilder withNickname(String nickname) {
		this.nickname = nickname;
		return this;
	}

	public Coordinator build() {
		return Coordinator.builder()
			.id(this.id)
			.nickname(this.nickname)
			.build();
	}
}
