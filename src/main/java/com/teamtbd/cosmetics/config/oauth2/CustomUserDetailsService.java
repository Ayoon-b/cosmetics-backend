package com.teamtbd.cosmetics.config.oauth2;

import com.teamtbd.cosmetics.config.oauth2.user.UserPrincipal;
import com.teamtbd.cosmetics.member.Member;
import com.teamtbd.cosmetics.member.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	@Autowired
	private MemberRepository memberRepository;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String email)
		throws UsernameNotFoundException {
		Member member = memberRepository.findByEmail(email)
			.orElseThrow(() ->
				new UsernameNotFoundException("User not found with email : " + email)
			);
		return UserPrincipal.create(member);
	}

	@Transactional
	public UserDetails loadUserById(Long id) {
		Member member = memberRepository.findById(id).orElseThrow(
			() -> new ResourceNotFoundException("User", "id", id)
		);
		return UserPrincipal.create(member);
	}
}
