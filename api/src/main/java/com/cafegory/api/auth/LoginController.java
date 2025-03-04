package com.cafegory.api.auth;

import com.cafegory.auth.domain.JwtToken;
import com.cafegory.auth.infrastructure.oauth2.dto.KakaoOAuth2TokenRequest;
import com.cafegory.auth.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class LoginController {

	private final LoginService loginService;

	@GetMapping("/kakao")
	public ResponseEntity<JwtToken> kakao(@RequestParam String code) {
		KakaoOAuth2TokenRequest kakaoOAuth2LoginRequest = new KakaoOAuth2TokenRequest(code);
		JwtToken jwtToken = loginService.socialLogin(kakaoOAuth2LoginRequest);
		return ResponseEntity.ok(jwtToken);
	}
}
