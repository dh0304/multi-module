package com.cafegory.api.member;

import com.cafegory.domain.member.domain.MemberContent;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class WelcomeProfileResponse {

	private String nickname;
	private String profileUrl;

	public static WelcomeProfileResponse from(MemberContent memberContent) {
		return new WelcomeProfileResponse(
			memberContent.getNickname(),
			memberContent.getImgUrl()
		);
	}
}
