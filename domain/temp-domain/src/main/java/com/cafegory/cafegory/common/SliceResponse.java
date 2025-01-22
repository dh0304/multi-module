package com.cafegory.cafegory.common;

import lombok.Getter;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Getter
public class SliceResponse<T> {

	private final boolean hasNext;
	private final Integer nextPage;
	private final List<T> content;

	private SliceResponse(boolean hasNext, Integer nextPage, List<T> content) {
		this.hasNext = hasNext;
		this.nextPage = hasNext ? nextPage : null;
		this.content = content;
	}

	public static <T> SliceResponse<T> of(boolean hasNext, Integer pageNumber, List<T> content) {
		return new SliceResponse<>(hasNext, pageNumber + 1, content);
	}

	public <U> SliceResponse<U> map(Function<T, U> converter) {
		List<U> convertedContent = this.content.stream()
			.map(converter)
			.collect(Collectors.toList());

		return new SliceResponse<>(this.hasNext, this.nextPage, convertedContent);
	}
}
