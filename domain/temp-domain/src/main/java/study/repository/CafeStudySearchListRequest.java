package study.repository;

import cafe.domain.CafeTagType;
import common.PagedRequest;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import study.domain.CafeStudyTagType;
import study.domain.MemberComms;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class CafeStudySearchListRequest extends PagedRequest {

//	@NotBlank
	//TODO NotBlank 기능이 동작해야한다. 어노테이션이 아니더라도 검증은 필요
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
