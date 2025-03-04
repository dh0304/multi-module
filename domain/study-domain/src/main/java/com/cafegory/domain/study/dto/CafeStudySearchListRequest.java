package com.cafegory.domain.study.dto;

import com.cafegory.domain.cafe.domain.CafeTagType;
import com.cafegory.domain.common.PagedRequest;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import com.cafegory.domain.study.domain.CafeStudyTagType;
import com.cafegory.domain.study.domain.MemberComms;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class CafeStudySearchListRequest extends PagedRequest {

	private String keyword;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate date;
	private CafeStudyTagType cafeStudyTag;
	private List<CafeTagType> cafeTags = new ArrayList<>();
	private MemberComms memberComms;

	protected CafeStudySearchListRequest() {
		super();
	}

	@Builder
	private CafeStudySearchListRequest(int page, int sizePerPage, String keyword,
		LocalDate date, CafeStudyTagType cafeStudyTagType,
		List<CafeTagType> cafeTagTypes, MemberComms memberComms) {
		super(page, sizePerPage);
		this.keyword = keyword;
		this.date = date;
		this.cafeStudyTag = cafeStudyTagType;
		this.cafeTags = cafeTagTypes;
		this.memberComms = memberComms;
	}

}
