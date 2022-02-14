package com.teamtbd.cosmetics.member.controller;


import com.teamtbd.cosmetics.member.Member;
import com.teamtbd.cosmetics.member.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "/", method = {RequestMethod.GET})
public class MemberController {

    @Autowired
    MemberRepository memberRepository;

    @PostMapping("/join") //api tester 에서 임시 회원가입 test하기위한 코드
    public String join(@RequestBody Member member){
        Member member1 = memberRepository.save(member);
        return member.getUser_name()+"님 안녕하세요.";
    }

    @GetMapping("/find")
    public Member findUser(@RequestParam Long id){
        Optional<Member> member=memberRepository.findById(id);

        return member.get(); //json타입으로 정보반환
    }

    //회원정보수정
    @PutMapping("/find")
    public Optional<Member> updateUser(@RequestParam Long id,@RequestBody Member member){
        Optional<Member> updateUser = memberRepository.findById(id);

        //바꿀수없는것 id,join date
        updateUser.ifPresent(selectUser->{
            selectUser.setUser_name(member.getUser_name());
            selectUser.setNickname(member.getNickname());
            selectUser.setBirth(member.getBirth());
            selectUser.setEmail(member.getEmail());
            selectUser.setGender(member.getGender());
            selectUser.setSkin_type(member.getSkin_type());
            selectUser.setPhonenumber(member.getPhonenumber());

            memberRepository.save(selectUser);
        });
        return updateUser;
    }
}
