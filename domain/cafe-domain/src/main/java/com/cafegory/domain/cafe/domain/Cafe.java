package com.cafegory.domain.cafe.domain;

import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
public class Cafe {

	private CafeId id;
	private String name;
	private String imgUrl;
	private String sns;
	private List<CafeTagType> cafeTagTypes = new ArrayList<>();
	private Address address;
	private List<Menu> menus;

	public boolean hasId(CafeId id) {
		return this.id.isSameId(id);
	}
}
