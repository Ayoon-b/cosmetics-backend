package com.teamtbd.cosmetics.member.controller;


import com.teamtbd.cosmetics.config.oauth2.ResourceNotFoundException;
import com.teamtbd.cosmetics.config.oauth2.user.CurrentUser;
import com.teamtbd.cosmetics.config.oauth2.user.UserPrincipal;
import com.teamtbd.cosmetics.member.Member;
import com.teamtbd.cosmetics.member.repository.MemberRepository;
import com.teamtbd.cosmetics.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/members")
public class MemberController {

	private final MemberService memberService;

	@Autowired //controller에서 리포지토리에 직접 접근하는 것은 안좋음. 임시 회원가입에만 사용하기
	MemberRepository memberRepository;


	@PostMapping("/") //api tester 에서 임시 회원가입 test하기위한 코드
	//Restful 한 url 를 짜기위해서 url에는 리소스를 나타내는 것이 좋음 "회원"등록이므로 url에 회원을 넣자
	public String join(@RequestBody Member member) {
		Member member1 = memberRepository.save(member);
		return member.getUserName() + "님 안녕하세요.";
	}

	@GetMapping("/")
	public Member findUser(@RequestParam Long id) {
		Optional<Member> member = memberRepository.findById(id);

		return member.get(); //json타입으로 정보반환
	}

	///////////여기부터 main기능
	//회원정보수정
	@PutMapping("/")
	public Optional<Member> updateUser(@RequestParam Long id, @RequestBody Member member) {
		return memberService.updateUser(id, member);
	}

	@GetMapping("/me")
	@PreAuthorize("hasRole('USER')")
	public Member getCurrentUser(@CurrentUser UserPrincipal userPrincipal) {
		return memberRepository.findById(userPrincipal.getId())
			.orElseThrow(() -> new ResourceNotFoundException("User", "id", userPrincipal.getId()));
	}

}
