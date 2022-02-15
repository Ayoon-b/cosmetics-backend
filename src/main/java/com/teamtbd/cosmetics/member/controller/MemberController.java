package com.teamtbd.cosmetics.member.controller;


import com.teamtbd.cosmetics.member.Member;
import com.teamtbd.cosmetics.member.repository.MemberRepository;
import com.teamtbd.cosmetics.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/", method = {RequestMethod.GET})
public class MemberController {

    private final MemberService memberService;

    @Autowired //controller에서 리포지토리에 직접 접근하는 것은 안좋음. 임시 회원가입에만 사용하기
    MemberRepository memberRepository;


    @PostMapping("/member") //api tester 에서 임시 회원가입 test하기위한 코드
    //Restful 한 url 를 짜기위해서 url에는 리소스를 나타내는 것이 좋음 "회원"등록이므로 url에 회원을 넣자
    public String join(@RequestBody Member member){
        Member member1 = memberRepository.save(member);
        return member.getUserName()+"님 안녕하세요.";
    }

    @GetMapping("/find")
    public Member findUser(@RequestParam Long id){
        Optional<Member> member=memberRepository.findById(id);

        return member.get(); //json타입으로 정보반환
    }

    ///////////여기부터 main기능
    //회원정보수정
    @PutMapping("/memberInfo")
    public Optional<Member> updateUser(@RequestParam Long id,@RequestBody Member member){
        return memberService.updateUser(id,member);
    }
}
