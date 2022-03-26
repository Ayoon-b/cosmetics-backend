package com.teamtbd.cosmetics.config.oauth2;

import com.teamtbd.cosmetics.config.AuthProvider;
import com.teamtbd.cosmetics.config.oauth2.user.OAuth2UserInfo;
import com.teamtbd.cosmetics.config.oauth2.user.OAuth2UserInfoFactory;
import com.teamtbd.cosmetics.config.oauth2.user.UserPrincipal;
import com.teamtbd.cosmetics.member.Member;
import com.teamtbd.cosmetics.member.Role;
import com.teamtbd.cosmetics.member.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Optional;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {
	@Autowired
	private MemberRepository memberRepository;

	@Override
	public OAuth2User loadUser(OAuth2UserRequest oAuth2UserRequest) throws OAuth2AuthenticationException {
		OAuth2User oAuth2User = super.loadUser(oAuth2UserRequest);
		try {
			return processOAuth2User(oAuth2UserRequest, oAuth2User);
		} catch (Exception ex) {
			throw new InternalAuthenticationServiceException(ex.getMessage(), ex.getCause());
		}
	}

	private OAuth2User processOAuth2User(OAuth2UserRequest oAuth2UserRequest, OAuth2User oAuth2User) {
		OAuth2UserInfo oAuth2UserInfo =
			OAuth2UserInfoFactory.getOAuth2UserInfo(oAuth2UserRequest.getClientRegistration().getRegistrationId(),
				oAuth2User.getAttributes());
		if (!StringUtils.hasText(oAuth2UserInfo.getEmail())) {
			throw new OAuth2AuthenticationProcessingException("Email not found from OAuth2 provider");
		}
		Optional<Member> memberOptional = memberRepository.findByEmail(oAuth2UserInfo.getEmail());

		Member member;
		if (memberOptional.isPresent()) {
			member = memberOptional.get();
			if (!member.getProvider().equals(AuthProvider.valueOf(oAuth2UserRequest.getClientRegistration().getRegistrationId().toUpperCase()))) {
				throw new OAuth2AuthenticationProcessingException("Looks like you're signed up with " +
																  member.getProvider() + " account. Please use your " +
																  member.getProvider() +
																  " account to login.");
			}
			member = updateMember(member, oAuth2UserInfo);
		} else {
			member = registerNewMember(oAuth2UserRequest, oAuth2UserInfo);
		}
		return UserPrincipal.create(member, oAuth2User.getAttributes());
	}

	private Member registerNewMember(OAuth2UserRequest oAuth2UserRequest, OAuth2UserInfo oAuth2UserInfo) {
		Member member = new Member();
		member.setProvider(AuthProvider.valueOf(oAuth2UserRequest.getClientRegistration().getRegistrationId().toUpperCase()));
		member.setProviderId(oAuth2UserInfo.getId());
		member.setUserName(oAuth2UserInfo.getName());
		member.setEmail(oAuth2UserInfo.getEmail());
		member.setPicture(oAuth2UserInfo.getImageUrl());
		member.setRole(Role.USER);
		return memberRepository.save(member);
	}

	private Member updateMember(Member member, OAuth2UserInfo oAuth2UserInfo) {
		member.setUserName(oAuth2UserInfo.getName());
		member.setPicture(oAuth2UserInfo.getImageUrl());
		return memberRepository.save(member);
	}
}
