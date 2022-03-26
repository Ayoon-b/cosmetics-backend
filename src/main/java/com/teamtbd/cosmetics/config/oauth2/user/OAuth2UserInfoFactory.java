package com.teamtbd.cosmetics.config.oauth2.user;

import com.teamtbd.cosmetics.config.AuthProvider;
import com.teamtbd.cosmetics.config.oauth2.OAuth2AuthenticationProcessingException;

import java.util.Map;

public class OAuth2UserInfoFactory {
	public static OAuth2UserInfo getOAuth2UserInfo(String registrationId, Map<String, Object> attributes) {
		if (registrationId.equalsIgnoreCase(AuthProvider.GOOGLE.toString())) {
			return new GoogleOAuth2UserInfo(attributes);
		} else if (registrationId.equalsIgnoreCase(AuthProvider.FACEBOOK.toString())) {
			return new FacebookOAuth2UserInfo(attributes);
		} else if (registrationId.equalsIgnoreCase(AuthProvider.KAKAO.toString())) {
			return new KakaoOAuth2UserInfo(attributes);
		} else if (registrationId.equalsIgnoreCase(AuthProvider.NAVER.toString())) {
			return new NaverOAuth2UserInfo(attributes);
		} else {
			throw new OAuth2AuthenticationProcessingException(
				"Sorry! Login with " + registrationId + " is not supported.");
		}
	}
}
