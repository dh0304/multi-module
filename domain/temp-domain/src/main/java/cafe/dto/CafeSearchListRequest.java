package cafe.dto;

import cafe.domain.CafeTagType;
import common.PagedRequest;
import lombok.Builder;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
public class CafeSearchListRequest extends PagedRequest {

	private String keyword;
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	private LocalDateTime openingDateTime;
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	private LocalDateTime closingDateTime;
	// cafeTags 로 필드명 유지할 것
	private List<CafeTagType> cafeTags = new ArrayList<>();

	protected CafeSearchListRequest() {
		super();
	}

	@Builder
	public CafeSearchListRequest(int page, int sizePerPage, String keyword, LocalDateTime openingDateTime,
		LocalDateTime closingDateTime, List<CafeTagType> cafeTagTypes
	) {
		super(page, sizePerPage);
		this.keyword = keyword;
		this.openingDateTime = openingDateTime;
		this.closingDateTime = closingDateTime;
		this.cafeTags = cafeTagTypes;
	}

	public void applyDefaultOpeningTime(LocalDateTime openingDateTime) {
		if (this.openingDateTime == null) {
			this.openingDateTime = openingDateTime;
		}
	}

	public void applyDefaultClosingTime(LocalDateTime closingDateTime) {
		if (this.closingDateTime == null) {
			this.closingDateTime = closingDateTime;
		}
	}
}
